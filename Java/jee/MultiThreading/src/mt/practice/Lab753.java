package mt.practice;

public class Lab753 {
	public static void main(String[] args) {
		MyThread753 t1 = new MyThread753();
		MyThread753 t2 = new MyThread753();
	}
}

class MyThread753 extends Thread {
	MyThread753() {
		start();
	}

	@Override
	public void run() {
		Thread th = Thread.currentThread();
		ThreadGroup tg = th.getThreadGroup();
		System.out.println("Thread name: " + th.getName());
		System.out.println("Thread group name: " + tg.getName());
	}
}