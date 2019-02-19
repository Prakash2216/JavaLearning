package com.javalearning.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProdMultiConsBBManager {
	final static int MAX_SIZE=10;
	
	static class ProdConsImplementation
	{
		private static volatile Queue<Integer> sharedQueue;
		Random random;
		
		public ProdConsImplementation()
		{
			sharedQueue = new LinkedList<>();
			random = new Random();
		}
		
		private void produce() throws InterruptedException
		{
			while(sharedQueue.size() == ProdMultiConsBBManager.MAX_SIZE)
			{
				synchronized(sharedQueue)
				{
					System.out.println("Shared Queue is Full "+Thread.currentThread().getName()+" is waiting, size "+sharedQueue.size());
					sharedQueue.wait();					
				}
			}
			synchronized(sharedQueue)
			{
				int nextVal = (int)random.nextInt(100);
				boolean added = sharedQueue.offer(nextVal);
				if(added)
				{
					System.out.println("Thread "+Thread.currentThread().getName()+" Produced to sharedQueue "+nextVal);
					sharedQueue.notifyAll();
				}
			}
		}
		
		private void consume() throws InterruptedException
		{
			while(sharedQueue.size() == 0)
			{
				synchronized(sharedQueue)
				{
					System.out.println("Shared Queue is empty "+Thread.currentThread().getName()+" is waiting, size "+sharedQueue.size());
					sharedQueue.wait();
				}
			}
			synchronized(sharedQueue)
			{
				Integer returnVal = sharedQueue.poll();
				if(returnVal != null)
				{
					System.out.println("Thread "+Thread.currentThread().getName()+" consumed "+returnVal+" in shared queue");
					sharedQueue.notifyAll();
				}
			}
		}
	}
	
	static class ProducerImplmentation implements Runnable
	{
		ProdConsImplementation pci;
		
		public ProducerImplmentation(ProdConsImplementation pci)
		{
			this.pci = pci;
		}
		
		@Override
		public void run()
		{
			while(true)
			{
				try
				{
					pci.produce();
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class ConsumerImplementation implements Runnable
	{
		ProdConsImplementation pci;
		
		public ConsumerImplementation(ProdConsImplementation pci)
		{
			this.pci=pci;
		}
		
		@Override
		public void run()
		{
			while(true)
			{
				try {
					pci.consume();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ProdConsImplementation pci = new ProdConsImplementation();
		
		Thread prodThread1 = new Thread(new ProducerImplmentation(pci), "Producer1");
		Thread ProdThread2 = new Thread(new ProducerImplmentation(pci), "Producer2");
		
		Thread consThread1 = new Thread(new ConsumerImplementation(pci), "Consumer1");
		Thread consThread2 = new Thread(new ConsumerImplementation(pci), "Consumer2");
		Thread consThread3 = new Thread(new ConsumerImplementation(pci), "Consumer3");
		
		
		prodThread1.start();
		ProdThread2.start();
		consThread1.start();
		consThread2.start();
		consThread3.start();

	}

}
