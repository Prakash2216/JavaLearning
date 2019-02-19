package com.javalearning.multithreading;

public class UseofClassLockInNonstaticMethodS 
{

	static class Shared 
	{
		public void display()
		{
			synchronized(Shared.class)
			{
				for(int i=0; i<100; i++)
				{
					System.out.println(Thread.currentThread().getName()+" : "+i);
				}
			}
		}
	}
	
	static class MyRunnable implements Runnable
	{
		Shared shared;
		
		MyRunnable(Shared shared)
		{
			this.shared = shared;
		}
		
		public void run()
		{
			shared.display();
		}
	}
	
	public static void main(String[] args) 
	{
		Shared obj = new UseofClassLockInNonstaticMethodS.Shared();
		Thread t1 = new Thread(new UseofClassLockInNonstaticMethodS.MyRunnable(obj));
		Thread t2 = new Thread(new UseofClassLockInNonstaticMethodS.MyRunnable(obj));
		
		t1.start();
		t2.start();
		
	}

}
