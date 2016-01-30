package hy.test;

import java.util.Map;

public class Observer {
	
	private String name;

	public Observer(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sayHello(Object sender, Map<String, Object> param){
		System.out.println(this.name);
		if(sender instanceof Sender){
			System.out.println(((Sender)sender).getName());
		}
		if(param.get("pwd") != null){
			System.out.println(param.get("pwd"));
		}
	}
}
