package hy.test;

public class SynchronizedTest {
	
	private String firstName = "hello";
	private String lastName = "world";
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void sayFirstName() throws InterruptedException{
		synchronized(this.firstName){
			Thread.sleep(1000);
			System.out.println(this.firstName);
		}
	}
	
	public void sayLastName() throws InterruptedException{
		synchronized(this.firstName){
			System.out.println(this.lastName);
		}
	}

	public void go(){
		thread1 t1 = new thread1();
		thread2 t2 = new thread2();
		
		t1.start();
		t2.start();
	}
	
	public class thread1 extends Thread implements Runnable{
		public void run(){
			try {
				System.out.println("enter1");
				SynchronizedTest.this.sayFirstName();
				System.out.println("end1");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class thread2 extends Thread implements Runnable{
		public void run(){
			try {
				System.out.println("enter2");
				SynchronizedTest.this.sayLastName();
				System.out.println("end2");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		SynchronizedTest a = new SynchronizedTest();
		a.go();
	}
	
}
