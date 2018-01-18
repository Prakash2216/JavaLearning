package com.javalearning.multithreading.ExecutorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceManager {

	static class Task implements Runnable
	{
		@Override
		public void run()
		{
			try 
			{
				long duration = (long)(Math.random()*20);
				System.out.println("Running task");
				TimeUnit.SECONDS.sleep(duration);
				System.out.println("Task completed");
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException 
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		try {
		executor.submit(new Task());
		System.out.println("Shuting down executor");
		executor.shutdown();
		executor.awaitTermination(2, TimeUnit.SECONDS);
		}
		catch(InterruptedException e)
		{
			System.err.println("tasks interrupted");
		}
		finally
		{
			if(!executor.isTerminated())
			{
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
	}

}
