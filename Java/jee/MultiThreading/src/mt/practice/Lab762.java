package mt.practice;

public class Lab762 {
	public static void main(String[] args) throws InterruptedException {
		MyThread762 t1 = new MyThread762();
		//Thread t1 = new Thread(th);
		System.out.println(t1.getState());
		t1.start();
		Thread.sleep(500);
		System.out.println(t1.getState());
		Thread.sleep(200);
		System.out.println(t1.getState());
	}
}

class MyThread762 extends Thread {
	@Override
	public void run() {
		Thread th = Thread.currentThread();
		for (int i = 1; i <= 10; i++) {
			System.out.println(th.getName() + " - value is :" + i + "\t" + th.getState());
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.run();
	}
}