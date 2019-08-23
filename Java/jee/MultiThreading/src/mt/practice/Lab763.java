package mt.practice;

public class Lab763 {
public static void main(String[] args) {
	Account acc = new Account();
	new AccountThread(acc);
}
}

class AccountThread implements Runnable {
	Account acc = null;

	AccountThread(Account acc) {
		this.acc = acc;
		Thread t1 = new Thread(this, "A");
		Thread t2 = new Thread(this, "B");
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			acc.withdraw(225);
			if (acc.getBal() < 0) {
				System.out.println("Amount is Overdrawn.....");
			}
		}
	}
}

class Account {
	int bal = 970;

	public void withdraw(int amt) {
		if (bal > amt) {
			System.out.println(Thread.currentThread().getName() + " is goint to withdraw...." + bal);
			try {
				Thread.sleep(1200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bal -= amt;
			System.out.println(Thread.currentThread().getName() + " is completed withdraw..:" + bal);
		} else {
			System.out.println("No Funds for " + Thread.currentThread().getName());
		}
	}

	public int getBal() {
		return bal;
	}
}
