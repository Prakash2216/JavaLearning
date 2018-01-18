package com.javalearning.multithreading;
/**
 * Producer consumer problem with the infinite buffer solving using the Guarded(Synchronized) block.
 * 
 * @author 1019270
 *
 */

import java.util.ArrayList;

class SharedObj
{
	private ArrayList<Integer> sharedList;
	
	SharedObj()
	{
		sharedList = new ArrayList<>();
	}
	
	public void produce(int num)
	{
		// Guarded Block
		synchronized (this) 
		{
			sharedList.add(num);
			notifyAll();
		}
	}
	
	public int consume()
	{
		synchronized(this)
		{
			if(sharedList.isEmpty())
				try
				{
					wait();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			return sharedList.remove(0);
		}
	}
}

class Producer implements Runnable
{
	SharedObj sharedObj = null;
	
	Producer(SharedObj sharedObj)
	{
		this.sharedObj=sharedObj;
	}
	@Override
	public void run() {
			
		for(int i=0; ;i++)
		{
			System.out.println("Produced : "+i);
			sharedObj.produce(i);
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}

class Consumer implements Runnable
{
	SharedObj sharedObj = null;
	
	Consumer(SharedObj sharedObj)
	{
		this.sharedObj=sharedObj;
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			int num = sharedObj.consume();
			System.out.println("Consumed : "+num);
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
public class ProducerConsmerProbUsingGuradedBlock {

	public static void main(String[] args) {
		
		SharedObj sharedObj = new SharedObj();
		
		Thread t1 = new Thread(new Producer(sharedObj));
		Thread t2 = new Thread(new Consumer(sharedObj));
		
		t1.start();
		t2.start();
	}

}
