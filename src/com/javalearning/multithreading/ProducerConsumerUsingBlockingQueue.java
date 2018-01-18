package com.javalearning.multithreading;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Producer consumer problem is solving using java.util.concurrent.BlockingQueue class
 * @author 1019270
 *
 */

class ProducerBQ implements Runnable
{
	BlockingQueue<Integer> sharedQ = null;
	
	ProducerBQ(BlockingQueue<Integer> sharedQ)
	{
		this.sharedQ = sharedQ;
	}
	@Override
	public void run() 
	{
		while(true) 
		{
			try {
				int num = new Random().nextInt(100);
				
				System.out.println("Produced : "+num);
				sharedQ.put(num);
				
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}

class ConsumerBQ implements Runnable
{
	BlockingQueue<Integer> sharedQ=null;
	
	ConsumerBQ(BlockingQueue<Integer> sharedQ)
	{
		this.sharedQ = sharedQ;
	}
	
	@Override
	public void run()
	{
		while(true)
		{			
			try
			{
				System.out.println("Consumed : "+sharedQ.take());
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
public class ProducerConsumerUsingBlockingQueue {

	public static void main(String[] args) {
		
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<>(100);
		
		ProducerBQ pr = new ProducerBQ(bq);
		ConsumerBQ con = new ConsumerBQ(bq);
		
		Thread t1 = new Thread(pr);
		Thread t2 = new Thread(con);
		
		t1.start();
		t2.start();
	}

}
