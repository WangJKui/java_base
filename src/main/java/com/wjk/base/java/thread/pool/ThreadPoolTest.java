package com.wjk.base.java.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
		ExecutorService executorService = new ThreadPoolExecutor(10, 10 ,60,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
				Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
	}
}
