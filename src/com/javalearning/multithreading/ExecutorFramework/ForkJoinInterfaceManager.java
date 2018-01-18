package com.javalearning.multithreading.ExecutorFramework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinInterfaceManager {

	static class Sum extends RecursiveTask<Long>
	{
		int low;
		int high;
		int [] array;
		
		Sum(int [] array, int low, int high)
		{
			this.array=array;
			this.low=low;
			this.high=high;
		}
		@Override
		protected Long compute() 
		{
			if(high - low <=10 )
			{
				long sum = 0;
				
				for(int i=low; i<high; ++i)
				{
					sum+=array[i];
				}
				return sum;
			}
			else
			{
				int mid = low+(high - low)/2;
				Sum left = new Sum(array, low, mid);
				Sum right = new Sum(array, mid, high);
				left.fork();
				long rightResult = right.compute();
				long leftResult = left.join();
				return leftResult+rightResult;
			}
		}		
	}
	public static void main(String[] args) 
	{
		int nThreads = Runtime.getRuntime().availableProcessors();
		System.out.println("The no of nThreads : "+nThreads);
		
		int [] numbers = new int[1000];
		
		for(int i=0; i<numbers.length; i++)
			numbers[i]=i;
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
		long result = forkJoinPool.invoke(new Sum(numbers, 0, numbers.length));
		System.out.println(result);
	}

}
