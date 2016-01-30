package hy;

import java.util.Map;

public class Notification {
	
	private Object sender;
	private Map<String,Object> param;
	
	public Object getSender() {
		return sender;
	}
	public void setSender(Object sender) {
		this.sender = sender;
	}
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	
	public static Notification createInstance(Object sender, Map<String, Object> param){
		Notification notification = new Notification();
		notification.setSender(sender);
		notification.setParam(param);
		return notification;
	}
	
}
