package com.wjk.base.java.thread.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.cnblogs.com/superfj/p/7543927.html
 * https://www.cnblogs.com/Wanted-Tao/p/6378942.html
 * @ClassName:  LockConditionTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年3月1日 上午8:50:00   
 *
 */
public class LockConditionTest {

	private final Lock lock = new ReentrantLock();

	private final Condition addCondition = lock.newCondition();

	private final Condition subCondition = lock.newCondition();

	private static int num = 0;

	private List<String> lists = new LinkedList<String>();

	public void add() {
		lock.lock();

		try {
			while(lists.size() == 10) {//当集合已满,则"添加"线程等待
				addCondition.await();
			}

			num++;
			lists.add("add Banana" + num);
			System.out.println("The Lists Size is " + lists.size());
			System.out.println("The Current Thread is " + Thread.currentThread().getName());
			System.out.println("==============================");
			this.subCondition.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {//释放锁
			lock.unlock();
		}
	}

	public void sub() {
		lock.lock();

		try {
			while(lists.size() == 0) {//当集合为空时,"减少"线程等待
				subCondition.await();
			}

			String str = lists.get(0);
			lists.remove(0);
			System.out.println("The Token Banana is [" + str + "]");
			System.out.println("The Current Thread is " + Thread.currentThread().getName());
			System.out.println("==============================");
			num--;
			addCondition.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}



	//==================================
	public static void main(String[] args) {
		LockConditionTest task = new LockConditionTest();

		/*Thread t1=new Thread(new AddThread(task));
		Thread t3=new Thread(new AddThread(task));
		Thread t7=new Thread(new AddThread(task));
		Thread t8=new Thread(new AddThread(task));
		Thread t2 = new Thread(new SubThread(task));
		Thread t4 = new Thread(new SubThread(task));
		Thread t5 = new Thread(new SubThread(task));
		Thread t6 = new Thread(new SubThread(task));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		*/
		ExecutorService pool = Executors.newFixedThreadPool(10);

		// 可以执行Runnable对象或者Callable对象代表的线程
		pool.submit(new AddThread(task));
		pool.submit(new AddThread(task));
		pool.submit(new AddThread(task));
		pool.submit(new AddThread(task));
		pool.submit(new AddThread(task));

		pool.submit(new SubThread(task));
		pool.submit(new SubThread(task));
		pool.submit(new SubThread(task));
		pool.submit(new SubThread(task));
		pool.submit(new SubThread(task));

		//结束线程池
		pool.shutdown();
	}

}

//==========================================
class AddThread implements Runnable {

	private LockConditionTest task;

	public AddThread(LockConditionTest task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.add();
	}

}

class SubThread implements Runnable {

	private LockConditionTest task;

	public SubThread(LockConditionTest task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.sub();
	}

}

