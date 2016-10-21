/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.FutureTask;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ThreadPoolUtils
/*    */ {
/* 21 */   private static final Log logger = LogFactory.getLog(ThreadPoolUtils.class);
/*    */   public static final int MAX_BATCH_COMMANDS = 50000;
/*    */ 
/*    */   public static <T> void executeCommands(List<Callable<T>> commands, boolean waitComplete, int poolSize)
/*    */   {
/* 33 */     double commandCount = commands.size();
/* 34 */     if (commandCount > 50000.0D) {
/* 35 */       double batchCount = Math.ceil(commandCount / 50000.0D);
/* 36 */       for (int i = 0; i < batchCount; ++i) {
/* 37 */         executeCommands(commands.subList(i * 50000, Math.min((i + 1) * 50000, new Double(commandCount).intValue())), waitComplete, poolSize);
/*    */       }
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 44 */       ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(poolSize);
/*    */ 
/* 46 */       if (logger.isDebugEnabled())
/* 47 */         logger.debug("创建线程池【" + poolSize + "】.");
/*    */       try
/*    */       {
/* 50 */         for (Callable command : commands) {
/* 51 */           executorService.execute(new FutureTask(command));
/*    */         }
/* 53 */         if (waitComplete) {
/* 54 */           while (executorService.getCompletedTaskCount() != executorService.getTaskCount()) {
/* 55 */             Thread.sleep(100L);
/*    */           }
/* 57 */           if (logger.isDebugEnabled())
/* 58 */             logger.debug("所有线程执行成功.");
/*    */         }
/*    */       }
/*    */       catch (Exception e) {
/*    */       }
/*    */       finally {
/* 64 */         executorService.shutdown();
/* 65 */         System.gc();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.ThreadPoolUtils
 * JD-Core Version:    0.5.4
 */