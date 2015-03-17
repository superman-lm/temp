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
		list.add("����-�Ϻ�");
		list.add("����-���");
		list.add("�Ϻ�-����");
		list.add("����-�Ϻ�");
		list.add("����-�Ͼ�");
	}

	@Override
	public void run() {
		for (int i = 0; i < SIZE; i++) {
			System.out.println("���ڻ�ȡ:" + Thread.currentThread().getName() + "��" + (i + 1) + "��Ʊ...");
		}

	}

	public static void main(String[] args) throws InterruptedException {

		// for (String item : list) {
		// // TestThread thread = new TestThread();
		// new Thread(new TestThread() ,item).start();
		// }

		// ��ʼ�ĵ�����
		final CountDownLatch begin = new CountDownLatch(1);

		// �����ĵ�����
		final CountDownLatch end = new CountDownLatch(10);

		// ʮ��ѡ��
		final ExecutorService exec = Executors.newFixedThreadPool(10);

		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// �����ǰ����Ϊ�㣬��˷����������ء�
						// �ȴ�
						begin.await();
						work(end, NO);
					} catch (Exception e) {
					}
				}
			};
			exec.submit(run);
		}
		System.out.println("Game Start");
		// begin��һ����ʼ��Ϸ
		begin.countDown();
		// �ȴ�end��Ϊ0��������ѡ�ֵ����յ�
		end.await();
		System.out.println("Game Over");
		exec.shutdown();
	}
	
	
	private static void work(CountDownLatch latch,int NO) {
		System.out.println("No." + NO + " arrived");
		latch.countDown();

	}

}
