package hy.test;

import java.util.Map;
import java.util.HashMap;

import hy.NotificationCenter;

public class Main {
	
	public static void main(String[] args){
		
		Observer ob1 = new Observer("ob1");
		Observer ob2 = new Observer("ob2");
		Observer ob3 = new Observer("ob3");
		Sender sen1 = new Sender("sen1");
		Sender sen2 = new Sender("sen2");
		Map<String,Object> param = new HashMap<String, Object>();
		
		try {
			NotificationCenter.getDefaultInstance().addObserver("sayhello", ob1, "sayHello", null);
			NotificationCenter.getDefaultInstance().addObserver("sayhello", ob2, "sayHello", sen1);
			NotificationCenter.getDefaultInstance().addObserver("sayhello", ob3, "sayHello", sen2);
			NotificationCenter.getDefaultInstance().postNotification("sayhello", null, param);
			NotificationCenter.getDefaultInstance().postNotification("sayhello", sen1, param);
			NotificationCenter.getDefaultInstance().postNotification("sayhello", sen2, param);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
