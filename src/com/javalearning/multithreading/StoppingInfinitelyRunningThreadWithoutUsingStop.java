package com.javalearning.multithreading;

import java.io.IOException;

class RThread implements Runnable
{
	public boolean continueThread=true;
	
	@Override
	public void run()
	{
		int i=0;
		while(true)
		{
			if(continueThread)
			{				
				try {
					System.out.println(i++);
					Thread.sleep(1000);
					System.out.println("Please press enter to stop "+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println(Thread.currentThread().getName()+"Ended");
				break;
				
			}
		}
	}
}
public class StoppingInfinitelyRunningThreadWithoutUsingStop {

	public static void main(String[] args) throws IOException 
	{
		RThread obj = new RThread();
		Thread thread = new Thread(obj, "Thread-1");
		
		thread.start();
		System.out.println(Thread.currentThread().getName()+"Wating for User to press enter");
		
		System.in.read();
		
		obj.continueThread=false;
	}

}
