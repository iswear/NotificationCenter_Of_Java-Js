package hy;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class NotificationObserver {
	private Object target;
	private Object sender;
	private String funName;
	
	public Object getTarget(){
		return this.target;
	}
	
	public Object getSender(){
		return this.sender;
	}
	
	public Object getFunName(){
		return this.funName;
	}
	
	private NotificationObserver(Object target, String funName, Object sender){
		this.target = target;
		this.funName = funName;
		this.sender = sender;
	}
	
	public void run(Object sender, Map<String, Object> param){
		try {
			target.getClass().getMethod(this.funName, Object.class, Map.class).invoke(target, sender, param);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static NotificationObserver createInstance(Object target, String funName, Object sender) throws NoSuchMethodException, SecurityException{
		if(target != null && funName != null){
			target.getClass().getMethod(funName, Object.class, Map.class);
			return new NotificationObserver(target, funName, sender);
		}else{
			return null;
		}
	}
}
