package com.keokim.ncphw;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloSpringApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void threadTest() throws InterruptedException {
		Account account = new Account();
		Thread th1 = new DrawThread(account);
		Thread th2 = new DrawThread(account);

		th1.start();
		th2.start();

		th1.join();
		th2.join();

		System.out.println(account.balance);
		//		ExecutorService executorService = Executors.newFixedThreadPool(10);
		//		executorService.execute(new DrawThread(account));
		//		executorService.execute(new DrawThread(account));
		//		executorService.execute(new DrawThread(account));
		//		executorService.execute(new DrawThread(account));
		//
		//		executorService.awaitTermination(1000L, );
		//		executorService.shutdown();
	}

	class Account {
		static int balance = 1000000;

		void draw(int amount) {
			balance -= amount;
		}
	}

	class DrawThread extends Thread {
		private Account account;

		public DrawThread(Account account) {
			this.account = account;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				synchronized (account) {
					account.draw(10);
				}
				// System.out.println(getName() + " draw " + account.balance);
			}
		}
	}
}
