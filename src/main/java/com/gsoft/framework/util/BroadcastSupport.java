package com.gsoft.framework.util;

import com.gsoft.framework.core.dataobj.Domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

public final class BroadcastSupport{
	private final Map<Class<?>, List<Object>> m_classToListeners;
	private final Map<Domain, List<Object>> m_targetToListeners;
	private final Map<Class<?>, Object> m_listenerToMulticast;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BroadcastSupport(){
	  this.m_classToListeners = Collections.synchronizedMap(new HashMap());
	  this.m_targetToListeners = Collections.synchronizedMap(new HashMap());
	  this.m_listenerToMulticast = Collections.synchronizedMap(new HashMap());
	}
	
	@SuppressWarnings("rawtypes")
	public void addListener(Domain target, Object listenerImpl){
	  Class listenerClass = getListenerClass(listenerImpl);
	  addListener(getClassListeners(listenerClass), listenerImpl);
	  if (target != null)
	    addListener(getTargetListeners(target), listenerImpl);
	}
	
	@SuppressWarnings("rawtypes")
	public void removeListener(Domain target, Object listenerImpl){
	  Class listenerClass = getListenerClass(listenerImpl);
	  getClassListeners(listenerClass).remove(listenerImpl);
	  if (target != null)
	    getTargetListeners(target).remove(listenerImpl);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void targetListener(Domain oldTarget, Domain newTarget){
	  List listeners = (List)this.m_targetToListeners.remove(oldTarget);
	  if (listeners != null)
	    getTargetListeners(newTarget).addAll(listeners);
	}
	
	@SuppressWarnings("rawtypes")
	private static Class<?> getListenerClass(Object listenerImpl){
	  Class implClass = listenerImpl.getClass();
	  Class listenerClass = implClass.getSuperclass();
	  if (listenerClass != Object.class) {
	    while (listenerClass.getSuperclass() != Object.class) {
	      listenerClass = listenerClass.getSuperclass();
	    }
	    return listenerClass;
	  }
	  Class[] interfaces = implClass.getInterfaces();
	  return interfaces[0];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getClassListeners(Class<?> listenerClass){
	  List listeners = (List)this.m_classToListeners.get(listenerClass);
	  if (listeners == null) {
	    listeners = new ArrayList();
	    this.m_classToListeners.put(listenerClass, listeners);
	  }
	  return listeners;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getTargetListeners(Domain target){
	  List listeners = (List)this.m_targetToListeners.get(target);
	  if (listeners == null) {
	    listeners = new ArrayList();
	    this.m_targetToListeners.put(target, listeners);
	  }
	  return listeners;
	}
	
	private static void addListener(List<Object> listeners, Object listener){
	  if (!listeners.contains(listener))
	    listeners.add(listener);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getListener(Class<T> listenerClass){
		Object listenerMulticast = this.m_listenerToMulticast.get(listenerClass);
		final Class<T> lisClass = listenerClass;
		if (listenerMulticast == null) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(listenerClass);
			Callback callback = new Callback() {
				@SuppressWarnings({ "rawtypes", "unused" })
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable
				{
				  List listeners = BroadcastSupport.this.getClassListeners(lisClass);
				  for (Object listener : listeners.toArray()) {
				    try {
				      method.invoke(listener, args);
				    } catch (InvocationTargetException e) {
				      throw e.getCause();
				    }
				  }
				
				  return null;
				}
			};
			enhancer.setCallback(callback);
			listenerMulticast = enhancer.create();
			this.m_listenerToMulticast.put(listenerClass, listenerMulticast);
		}
		
		Object casted_listenerMulticast = listenerMulticast;
		return (T) casted_listenerMulticast;
	}
}
