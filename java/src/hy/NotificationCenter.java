package hy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NotificationCenter {
	
	/*
	 * 是否需要加锁视具体情况而言(程序中已加锁)
	 * 1、对于在多个线程中频繁的 addObserver 或者 removeObserver 操作的需要加锁
	 *     1.1、多线程的APP应用
	 * 2、对于只在一个线程中调用或者程序初始化前就已经完成addObserver而后续没有进行额外的addObserver 或者 removeObserver 的可以不加锁，提高性能
	 *     2.1、严格控制的APP应用
	 *     2.2、WEBAPP应用程序
	 */
	
	private static NotificationCenter defaultInstance = new NotificationCenter();
	
	private Map<String,List<NotificationObserver>> observers = new HashMap<String, List<NotificationObserver>>();
	
	public void addObserver(String name, NotificationObserver observer){
		if(observer != null){
			List<NotificationObserver> observersInName = this.observers.get(name);
			if(observersInName == null){
				observersInName = new LinkedList<NotificationObserver>();
				this.observers.put(name, observersInName);
			}
			synchronized(observersInName){
				observersInName.add(observer);
			}
		}
	}
	
	public void addObserver(String name, Object target, String funName, Object sender) throws NoSuchMethodException, SecurityException{
		NotificationObserver observer = NotificationObserver.createInstance(target, funName, sender);
		this.addObserver(name, observer);
	}
	
	public void removeObserver(String name, NotificationObserver observer){
		List<NotificationObserver> observersInName = this.observers.get(name);
		if(observersInName != null){
			synchronized(observersInName){
				Iterator<NotificationObserver> iterator = observersInName.iterator();
				while(iterator.hasNext()){
					if(iterator.next() == observer){
						iterator.remove();
					}
				}
			}
		}
	}
	
	public void removeObserver(String name, Object target, String funName, Object sender){
		List<NotificationObserver> observersInName = this.observers.get(name);
		if(observersInName != null){
			synchronized(observersInName){
				Iterator<NotificationObserver> iterator = observersInName.iterator();
				while(iterator.hasNext()){
					NotificationObserver observer = iterator.next();
					if(observer.getTarget() == target && observer.getFunName().equals(funName) && observer.getSender() == sender){
						iterator.remove();
					}
				}
			}
		}
	}
	
	public void postNotification(String name, Notification notification){
		List<NotificationObserver> observersInName = this.observers.get(name);
		if(observersInName != null){
			synchronized(observersInName){
				Iterator<NotificationObserver> iterator = observersInName.iterator();
				while(iterator.hasNext()){
					NotificationObserver observer = iterator.next();
					if(observer.getSender() == null || observer.getSender() == notification.getSender()){
						observer.run(notification.getSender(), notification.getParam());
					}
				}
			}
		}
	}
	
	public void postNotification(String name, Object sender, Map<String, Object> param){
		List<NotificationObserver> observersInName = this.observers.get(name);
		if(observersInName != null){
			synchronized(observersInName){
				Iterator<NotificationObserver> iterator = observersInName.iterator();
				while(iterator.hasNext()){
					NotificationObserver observer = iterator.next();
					if(observer.getSender() == null || observer.getSender() == sender){
						observer.run(sender, param);
					}
				}
			}
		}
	}
	
	public static NotificationCenter getDefaultInstance(){
		return defaultInstance;
	}
}
