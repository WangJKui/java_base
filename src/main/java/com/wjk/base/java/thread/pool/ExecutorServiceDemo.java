package com.wjk.base.java.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * https://www.cnblogs.com/wihainan/p/4765862.html
 * @ClassName:  MyRunnable   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年2月28日 下午5:32:51   
 *
 */
class MyRunnable implements Runnable {
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			System.out.println(Thread.currentThread().getName() + ":" + x);
		}
	}
}
/**
 * https://blog.csdn.net/feinifi/article/details/78194373
 * @ClassName:  MyCallable   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年2月28日 下午5:32:40   
 *
 */
class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int a = 0;
		for (int x = 0; x < 10; x++) {
			a+=x;
		}
		return a;
	}
}


public class ExecutorServiceDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		callTest();
	}

	public static void runTest() {
		// 创建一个线程池对象，控制要创建几个线程对象。
		// public static ExecutorService newFixedThreadPool(int nThreads)
		ExecutorService pool = Executors.newFixedThreadPool(2);

		// 可以执行Runnable对象或者Callable对象代表的线程
		pool.submit(new MyRunnable());
		pool.submit(new MyRunnable());

		//结束线程池
		pool.shutdown();
	}

	public static void callTest() throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		Future<Integer> res = null;
		for(int i=0;i<5;i++){
			res = threadPool.submit(new MyCallable());
			futures.add(res);
		}
		threadPool.shutdown();
		for(Future<Integer> future:futures){
			System.out.println(future.get());
		}

	}
}