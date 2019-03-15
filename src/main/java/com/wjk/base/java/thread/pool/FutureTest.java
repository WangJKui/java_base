package com.wjk.base.java.thread.pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * https://blog.csdn.net/hongtaolong/article/details/83349705
 * https://www.jianshu.com/p/949d44f3d9e3
 * https://blog.csdn.net/u014209205/article/details/80598209
 * @ClassName:  FutureTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年2月28日 下午4:47:13   
 *
 */
public class FutureTest {
	
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		 List<Integer> a = Arrays.asList(1,1,1,1);
		 List<Integer> b = Arrays.asList(2,2,2,2);

		System.out.println(getTotal(a,b));
	}
	
	public static int getTotal(final List<Integer> a, final List<Integer> b) throws ExecutionException, InterruptedException {
	    Future<Integer> future = Executors.newCachedThreadPool().submit(new Callable<Integer>() {
	        @Override
	        public Integer call() throws Exception {
	            int r = 0;
	            for (int num : a) {
	                r += num;
	            }
	            return r;
	        }
	    });

	    int r = 0;
	    for (int num : b) {
	        r += num;
	    }
	    return r + future.get();
	}
}
