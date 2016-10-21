/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/*     */ 
/*     */ public class TimerJobUtils
/*     */ {
/*  30 */   private static final Log logger = LogFactory.getLog(TimerJobUtils.class);
/*     */ 
/*     */   private static class JobResult<T, R>
/*     */   {
/*     */     private Future<R> future;
/*     */     private T data;
/*     */     private long jobStartTime;
/*     */ 
/*     */     public JobResult(long jobStartTime, T data, Future<R> future)
/*     */     {
/* 407 */       this.jobStartTime = jobStartTime;
/* 408 */       this.future = future;
/* 409 */       this.data = data;
/*     */     }
/*     */ 
/*     */     public Future<R> getFuture() {
/* 413 */       return this.future;
/*     */     }
/*     */ 
/*     */     public long getJobStartTime()
/*     */     {
/* 418 */       return this.jobStartTime;
/*     */     }
/*     */ 
/*     */     public T getData() {
/* 422 */       return this.data;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class JobCheckTimerTask<T, R> extends TimerTask
/*     */   {
/*     */     private TimerJobUtils.TimerJobSchedule<T, R> timerJobSchedule;
/*     */ 
/*     */     public JobCheckTimerTask(TimerJobUtils.TimerJobSchedule<T, R> timerJobSchedule)
/*     */     {
/* 369 */       this.timerJobSchedule = timerJobSchedule;
/*     */     }
/*     */ 
/*     */     public void run()
/*     */     {
/* 374 */       TimerJobUtils.JobTimerTask jobTimerTask = this.timerJobSchedule.getJobTimerTask();
/*     */ 
/* 376 */       if (jobTimerTask != null)
/* 377 */         if (needRestart(jobTimerTask)) {
/* 378 */           TimerJobUtils.logger.info("重新启动任务.");
/* 379 */           this.timerJobSchedule.restart();
/*     */         }
/* 381 */         else if (jobTimerTask != null) {
/* 382 */           jobTimerTask.consumeResults();
/*     */         }
/*     */     }
/*     */ 
/*     */     private boolean needRestart(TimerJobUtils.JobTimerTask<T, R> jobTimerTask)
/*     */     {
/* 393 */       return (jobTimerTask.getLastCompleteTime() > 0L) && (System.currentTimeMillis() - jobTimerTask.getLastCompleteTime() > 600000L);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class TimerJobRunnable<T, R>
/*     */     implements Callable<R>
/*     */   {
/*     */     private T data;
/*     */     private TimerJobUtils.JobTimerTask<T, R> jobTimerTask;
/*     */     private int batchIndex;
/*     */     private int index;
/*     */     private String jobId;
/*     */ 
/*     */     public TimerJobRunnable(TimerJobUtils.JobTimerTask<T, R> jobTimerTask, String jobId, int batchIndex, int index, T data)
/*     */     {
/* 341 */       this.jobTimerTask = jobTimerTask;
/* 342 */       this.batchIndex = batchIndex;
/* 343 */       this.index = index;
/* 344 */       this.data = data;
/* 345 */       this.jobId = jobId;
/*     */     }
/*     */ 
/*     */     public R call()
/*     */     {
/* 350 */       if (this.jobTimerTask != null) {
/* 351 */         return this.jobTimerTask.callJob(this.jobId, this.batchIndex, this.index, this.data);
/*     */       }
/* 353 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class JobTimerTask<T, R> extends TimerTask
/*     */   {
/* 165 */     private AtomicInteger batchCounter = new AtomicInteger();
/*     */     private TimerJobUtils.TimerJob<T, R> timerJob;
/* 170 */     private volatile ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
/*     */     private volatile long lastCompleteTime;
/*     */     private volatile int timeout;
/* 178 */     private List<R> results = Collections.synchronizedList(new ArrayList());
/*     */ 
/* 180 */     private Map<String, TimerJobUtils.JobResult<T, R>> jobFutures = Collections.synchronizedMap(new HashMap());
/*     */ 
/*     */     private JobTimerTask(TimerJobUtils.TimerJob<T, R> timerJob) {
/* 183 */       this.timerJob = timerJob;
/* 184 */       this.timeout = timerJob.getTimeout();
/* 185 */       int queueCapacity = timerJob.getPoolSize();
/* 186 */       this.executor.setMaxPoolSize(queueCapacity);
/* 187 */       this.executor.setQueueCapacity(queueCapacity);
/* 188 */       this.executor.setCorePoolSize(queueCapacity);
/*     */ 
/* 190 */       this.executor.setAllowCoreThreadTimeOut(true);
/* 191 */       this.executor.setWaitForTasksToCompleteOnShutdown(true);
/*     */ 
/* 193 */       this.executor.initialize();
/*     */     }
/*     */     public void run() {
/*     */       int batchIndex;
/*     */       int index;
/*     */       Iterator i$;
/*     */       try {
/* 200 */         long fetchSize = this.executor.getMaxPoolSize() - this.executor.getThreadPoolExecutor().getActiveCount();
/* 201 */         batchIndex = this.batchCounter.addAndGet(1);
/*     */ 
/* 204 */         if ((this.jobFutures.size() > 0) && (this.timeout > 0)) {
/* 205 */           processTimeoutJobs();
/*     */         }
/*     */ 
/* 208 */         if (fetchSize > 0L) {
/* 209 */           if (TimerJobUtils.logger.isDebugEnabled()) {
/* 210 */             TimerJobUtils.logger.debug("任务批次:" + batchIndex + ",新添加任务：" + fetchSize + "个.");
/*     */           }
/*     */ 
/* 213 */           List datas = this.timerJob.getDatas(batchIndex, fetchSize);
/* 214 */           if (datas != null) {
/* 215 */             index = 0;
/* 216 */             for (i$ = datas.iterator(); i$.hasNext(); ) { Object data = i$.next();
/* 217 */               if (data != null) {
/* 218 */                 long jobStartTime = System.currentTimeMillis();
/* 219 */                 String jobId = jobStartTime + "_" + batchIndex + "_" + index;
/* 220 */                 Future future = this.executor.submit(new TimerJobUtils.TimerJobRunnable(this, jobId, batchIndex, index, data));
/*     */ 
/* 222 */                 this.jobFutures.put(jobId, new TimerJobUtils.JobResult(jobStartTime, data, future));
/*     */ 
/* 224 */                 ++index;
/*     */               } }
/*     */ 
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Throwable e)
/*     */       {
/* 232 */         TimerJobUtils.logger.error(e.getMessage());
/*     */       }
/*     */       finally {
/* 235 */         setLastCompleteTime(System.currentTimeMillis());
/*     */       }
/*     */     }
/*     */ 
/*     */     private synchronized void processTimeoutJobs()
/*     */     {
/* 243 */       long currentTime = System.currentTimeMillis();
/*     */ 
/* 245 */       for (Map.Entry entry : this.jobFutures.entrySet()) {
/* 246 */         long runningTime = currentTime - ((TimerJobUtils.JobResult)entry.getValue()).getJobStartTime();
/*     */ 
/* 249 */         long deltTime = runningTime - this.timeout * 1000;
/* 250 */         if (deltTime > 0L)
/*     */           try
/*     */           {
/* 253 */             Object result = ((TimerJobUtils.JobResult)entry.getValue()).getFuture().get(10L, TimeUnit.MILLISECONDS);
///* 254 */             if (result != null)
///* 255 */               addResult((String)entry.getKey(), result);
/*     */           }
/*     */           catch (InterruptedException e) {
/* 258 */             e.printStackTrace();
/*     */           } catch (ExecutionException e) {
/* 260 */             e.printStackTrace();
/*     */           } catch (TimeoutException e) {
/* 262 */             TimerJobUtils.logger.warn("停止执行超时的任务:" + (String)entry.getKey() + ",已经超时" + deltTime + "毫秒.");
/*     */ 
///* 264 */             this.timerJob.processTimeoutJob((String)entry.getKey(), ((TimerJobUtils.JobResult)entry.getValue()).getData());
/*     */           }
/*     */       }
/*     */     }
/*     */ 
/*     */     public synchronized long getLastCompleteTime()
/*     */     {
/* 271 */       return this.lastCompleteTime;
/*     */     }
/*     */ 
/*     */     private synchronized void setLastCompleteTime(long lastCompleteTime) {
/* 275 */       this.lastCompleteTime = lastCompleteTime;
/*     */     }
/*     */ 
/*     */     public synchronized void addResult(String jobId, R result) {
/* 279 */       if (result != null) {
/* 280 */         this.results.add(result);
/* 281 */         if (!this.jobFutures.containsKey(jobId))
/*     */           return;
/* 283 */         this.jobFutures.remove(jobId);
/*     */       }
/*     */     }
/*     */ 
/*     */     protected R callJob(String jobId, int batchIndex, int index, T data)
/*     */     {
/* 295 */       Object result = null;
/*     */       try {
/* 297 */         result = this.timerJob.call(data);
///* 298 */         addResult(jobId, result);
/* 299 */         if (TimerJobUtils.logger.isDebugEnabled())
/* 300 */           TimerJobUtils.logger.info(jobId + " end at:" + System.currentTimeMillis());
/*     */       }
/*     */       catch (Throwable e) {
/* 303 */         e.printStackTrace();
/*     */       }
/*     */       finally {
/*     */       }
/* 307 */       return (R) result;
/*     */     }
/*     */ 
/*     */     public void consumeResults() {
/* 311 */       List rets = new ArrayList();
/* 312 */       if (this.results != null) {
/* 313 */         rets.addAll(this.results);
/* 314 */         this.results.clear();
/*     */ 
/* 316 */         this.timerJob.processResults(rets);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class TimerJobSchedule<T, R>
/*     */   {
/*     */     private volatile TimerJobUtils.JobTimerTask<T, R> jobTimerTask;
/*     */     private volatile TimerJobUtils.JobCheckTimerTask<T, R> jobCheckTimerTask;
/*  92 */     private volatile Timer timer = new Timer();
/*     */ 
/*  94 */     private volatile Timer checkTimer = new Timer();
/*     */     private volatile TimerJobUtils.TimerJob<T, R> timerJob;
/*     */ 
/*     */     public TimerJobSchedule(TimerJobUtils.TimerJob<T, R> timerJob)
/*     */     {
/* 103 */       this.timerJob = timerJob;
/* 104 */       this.jobTimerTask = createJobTimerTask();
/* 105 */       this.jobCheckTimerTask = new TimerJobUtils.JobCheckTimerTask(this);
/*     */     }
/*     */ 
/*     */     public synchronized void start()
/*     */     {
/* 111 */       if (this.timerJob != null) {
/* 112 */         int period = this.timerJob.getPeriod();
/* 113 */         this.timer.schedule(this.jobTimerTask, 0L, period);
/*     */ 
/* 115 */         this.checkTimer.schedule(this.jobCheckTimerTask, period / 3, period / 2);
/*     */       }
/*     */     }
/*     */ 
/*     */     public synchronized void restart()
/*     */     {
/* 123 */       if (this.timer != null) {
/* 124 */         this.timer.cancel();
/*     */       }
/* 126 */       this.jobTimerTask = createJobTimerTask();
/* 127 */       this.timer = new Timer();
/* 128 */       this.timer.schedule(this.jobTimerTask, 0L, this.timerJob.getPeriod());
/*     */     }
/*     */ 
/*     */     public synchronized void stop()
/*     */     {
/* 135 */       this.timer.cancel();
/* 136 */       this.jobTimerTask.consumeResults();
/* 137 */       this.checkTimer.cancel();
/*     */     }
/*     */ 
/*     */     private TimerJobUtils.JobTimerTask<T, R> createJobTimerTask()
/*     */     {
	return jobTimerTask;
///* 145 */       return new TimerJobUtils.JobTimerTask(this.timerJob, null);
/*     */     }
/*     */ 
/*     */     public synchronized TimerJobUtils.JobTimerTask<T, R> getJobTimerTask()
/*     */     {
/* 152 */       return this.jobTimerTask;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract interface TimerJob<T, R>
/*     */   {
/*     */     public abstract R call(T paramT);
/*     */ 
/*     */     public abstract List<T> getDatas(int paramInt, long paramLong);
/*     */ 
/*     */     public abstract void processResults(List<R> paramList);
/*     */ 
/*     */     public abstract int getPeriod();
/*     */ 
/*     */     public abstract int getPoolSize();
/*     */ 
/*     */     public abstract int getTimeout();
/*     */ 
/*     */     public abstract void processTimeoutJob(String paramString, T paramT);
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.TimerJobUtils
 * JD-Core Version:    0.5.4
 */