package com.wjk.base.java.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：实现读写分离，读读共享，读写互斥，写写互斥 
 * 读写锁适用于读多写少的情况，性能远高于重入锁ReentrantLock 
 * https://www.cnblogs.com/whatadiors/p/8013086.html
 * @ClassName:  ReentrantReadWriteLockTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年3月1日 上午9:14:34   
 *
 */
public class ReentrantReadWriteLockTest {
	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = reentrantReadWriteLock.readLock();
	private static Lock writeLock = reentrantReadWriteLock.writeLock();
	private static int value;

	public static Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);// 模拟读操作
			System.out.println("读操作:" + value);
			return value;
		} finally {
			lock.unlock();
		}
	}

	public static void handleWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);// 模拟写操作
			System.out.println("写操作:" + value);
			value = index;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws Exception {
		TestReadThread testReadThread = new TestReadThread();
		TestWriteThread testWriteThread = new TestWriteThread();
		for (int i = 0; i < 18; i++) {
			new Thread(testReadThread).start();
		}
		for (int i = 18; i < 20; i++) {
			new Thread(testWriteThread).start();
		}

	}

	private static class TestReadThread extends Thread {
		@Override
		public void run() {
			try {
				//ReentrantReadWriteLockTest.handleRead(lock);
				ReentrantReadWriteLockTest.handleRead(readLock);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class TestWriteThread extends Thread {
		@Override
		public void run() {
			try {
				//ReentrantReadWriteLockTest.handleWrite(lock,new Random().nextInt(100));
				ReentrantReadWriteLockTest.handleWrite(writeLock,new Random().nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
