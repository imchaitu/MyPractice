package mt.practice;

public class Lab754 {
public static void main(String[] args) {
	MyThread754 t1 = new MyThread754();
	MyThread754 t2 = new MyThread754();
}
}

class MyThread754 implements Runnable {
	
	public MyThread754() {
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		Thread th = Thread.currentThread();
		ThreadGroup tg = th.getThreadGroup();
		System.out.println("Thread Name: " + th.getName());
		System.out.println("Thread group Name: " + tg.getName());
	}
}