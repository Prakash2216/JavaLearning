package com.javalearning.multithreading;

class Shared
{
	public synchronized void test()
	{
		System.out.println("this is test synchronized method");
		try
		{
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
		test1();
	}
	
	public synchronized void test1()
	{
		System.out.println("This is synchronized test1 method");
		System.out.println(Thread.currentThread().getName());
		for(int i=0; i<50; i++)
		{
			System.out.print(i);
		}
	}
}

class Thread1 implements Runnable
{
	Shared objLock = null;
	
	Thread1(Shared objLock)
	{
		this.objLock=objLock;
	}
	@Override
	public void run() 
	{
		objLock.test();
	}
}
public class ReentrantSynchronization {

	public static void main(String[] args) {
		Shared obj = new Shared();
		Thread1 t1 = new Thread1(obj);
		Thread1 t2 = new Thread1(obj);
		
		Thread t = new Thread(t1);
		Thread t3 = new Thread(t2);
		t.start();
		t3.start();
		System.out.println("End of main thread");
	}

}
