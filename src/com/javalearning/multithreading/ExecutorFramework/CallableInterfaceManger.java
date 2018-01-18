package com.javalearning.multithreading.ExecutorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableInterfaceManger {

	static class FactorialService implements  Callable<Long>
	{
		private int number;
		
		FactorialService(int number)
		{
			this.number=number;
		}
		
		@Override
		public Long call() throws Exception {
			return factorial();
		}
		
		private Long factorial() throws InterruptedException
		{
			long result=1;
			
			while(number != 0)
			{
				result = result*number;
				number--;
				
				Thread.sleep(100);
			}
			return result;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException 
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		System.out.println("Factorial Service is get called for 10! ");
		Future<Long> future_result10 = executor.submit(new FactorialService(10));
		
		System.out.println("Factorial Service is get called for 20! ");
		Future<Long> future_result20 = executor.submit(new FactorialService(20));
		
		Long factorial10 = future_result10.get();
		System.out.println("10! = "+factorial10);
		
		Long factorial20 = future_result20.get();
		System.out.println("20! = "+factorial20);
		
		executor.shutdown();
	}

}
