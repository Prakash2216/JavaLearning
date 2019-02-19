package com.javalearning.multithreading;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bounded Buffer Problem using Lock and Condition Interface.
 * @author 1019270
 *
 */

class BoundedBuffer
{
	Lock lock = new ReentrantLock();
	
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();
	
	final Object [] items = new Object[100];
	int putPtr, takePtr, count;
	
	public void put(Object x) throws InterruptedException
	{
		lock.lock();
		
		try
		{
			while(count == items.length)
				notFull.await();
			System.out.println(Thread.currentThread().getName());
			items[putPtr++]=x;
			
			if(++putPtr == items.length)
				putPtr=0;
			count++;
			
			notEmpty.signal();
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public Object take() throws InterruptedException
	{
		lock.lock();
		
		try
		{
			while(count == 0)
				notEmpty.await();
			System.out.println(Thread.currentThread().getName());
			Object x = items[takePtr++];
			
			if(++takePtr == items.length)
				takePtr=0;
			count--;
			notFull.signal();
			return x;
		}
		finally
		{
			lock.unlock();
		}
	}
}

class BBProducer implements Runnable
{
	BoundedBuffer bbObj = null;
	
	BBProducer(BoundedBuffer bbObj)
	{
		this.bbObj = bbObj;
	}
	
	@Override
	public void run()
	{
		
		for(int i=0; ;i++)
		{			
			try 
			{
				bbObj.put(i);
				System.out.println("Produced : "+i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}

class BBConsumer implements Runnable
{
	BoundedBuffer bbObj = null;
	
	BBConsumer(BoundedBuffer bbObj)
	{
		this.bbObj=bbObj;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try {
				System.out.println("Consumed : "+(int)bbObj.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class ProducerConsumerULockAndCond 
{
	public static void main(String[] args) 
	{
		BoundedBuffer bbObj = new BoundedBuffer();
		
		/*Thread t1 = new Thread(new BBProducer(bbObj));
		Thread t2 = new Thread(new BBConsumer(bbObj));
		
		t1.start();
		t2.start();*/
		
		//Running a runnable task using Executor 
		Executor ex = Executors.newCachedThreadPool();
		ex.execute(new BBProducer(bbObj));
		ex.execute(new BBConsumer(bbObj));
		ex.execute(new BBConsumer(bbObj));
		ex.execute(new BBConsumer(bbObj));
		
		ThreadPoolExecutor pool = (ThreadPoolExecutor)ex;
		pool.shutdown();
	}
}
