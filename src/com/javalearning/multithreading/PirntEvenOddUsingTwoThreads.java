package com.javalearning.multithreading;

public class PirntEvenOddUsingTwoThreads {

	static Object monitor = new Object();
	static volatile int counter =1;
	
	static class EvenOddImplementation implements Runnable
	{
		int threadId;
		public EvenOddImplementation(int threadId) {
			this.threadId = threadId;
		}
		public void run()
		{
			printEvenOdd();
		}
		
		private void printEvenOdd()
		{
			try
			{
				while(true)
				{
					Thread.sleep(1000);
					synchronized(monitor)
					{
						if(threadId == 2) 
						{
							if(counter%2 == 0)
							{
								System.out.println(Thread.currentThread().getName()+" Produced "+counter);
								counter++;
								monitor.notify();
							}
							else {
								monitor.wait();
							}
						}
						if(threadId == 1)
						{
							if(counter%2 != 0)
							{
								System.out.println(Thread.currentThread().getName()+" Produced "+counter);
								counter++;
								monitor.notify();
							}
							else
							{
								monitor.wait();
							}
						}
					}
				}
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) 
	{
		
		Thread t1 = new Thread(new EvenOddImplementation(2), "Even-Thread");
		Thread t2 = new Thread(new EvenOddImplementation(1), "Odd-Thread");
		
		t1.start();
		t2.start();
	}

}
