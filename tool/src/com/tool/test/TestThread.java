package com.tool.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread implements Runnable {

	private static List<String> list = new ArrayList<String>();
	private static int SIZE = 5;

	static {
		list.add("北京-上海");
		list.add("北京-天津");
		list.add("上海-大连");
		list.add("深圳-上海");
		list.add("北京-南京");
	}

	@Override
	public void run() {
		for (int i = 0; i < SIZE; i++) {
			System.out.println("正在获取:" + Thread.currentThread().getName() + "第" + (i + 1) + "张票...");
		}

	}

	public static void main(String[] args) throws InterruptedException {

		// for (String item : list) {
		// // TestThread thread = new TestThread();
		// new Thread(new TestThread() ,item).start();
		// }

		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);

		// 结束的倒数锁
		final CountDownLatch end = new CountDownLatch(10);

		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);

		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 如果当前计数为零，则此方法立即返回。
						// 等待
						begin.await();
						work(end, NO);
					} catch (Exception e) {
					}
				}
			};
			exec.submit(run);
		}
		System.out.println("Game Start");
		// begin减一，开始游戏
		begin.countDown();
		// 等待end变为0，即所有选手到达终点
		end.await();
		System.out.println("Game Over");
		exec.shutdown();
	}
	
	
	private static void work(CountDownLatch latch,int NO) {
		System.out.println("No." + NO + " arrived");
		latch.countDown();

	}

}
