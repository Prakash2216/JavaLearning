package com.javalearning.multithreading.ExecutorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceManager
{
	static class BeepTask implements Runnable
	{
		@Override 
		public void run()
		{
			System.out.println("beep");
		}
	}
	
	public static void main(String[] args)
	{
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		//schedleAtFixedRate method is scheduling the Runnable task to run after each 2 seconds.
		//by starting the task after 2 seconds
		ScheduledFuture<?> beepHandler = scheduler.scheduleAtFixedRate(new BeepTask(), 2, 2, TimeUnit.SECONDS);
		
		// By using the beepHandler of ScheduledFuture be can handle the task.
		//Cancelling the beepTask and shuting down the scheduled Executor after running 10 seconds
		scheduler.schedule(new Runnable() {
			
			@Override
			public void run() {
				beepHandler.cancel(true);
				scheduler.shutdown();
			}
		}, 10, TimeUnit.SECONDS);
	}

}
