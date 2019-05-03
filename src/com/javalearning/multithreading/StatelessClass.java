package com.javalearning.multithreading;

class StatelessShared
{
	public void print()
	{
		for(int i=0; i<100; i++)
		{
			System.out.println(Thread.currentThread().getName()+" : "+i);
		}
	}
}

class CustomThread implements Runnable
{
	StatelessShared obj;
	public CustomThread(StatelessShared stl)
	{
		this.obj = stl;
	}
	@Override
	public void run() {
		obj.print();		
	}
}
public class StatelessClass 
{

	public static void main(String[] args) 
	{
		StatelessShared obj = new StatelessShared();
		CustomThread t1 = new CustomThread(obj);
		CustomThread t2 = new CustomThread(obj);
		Thread th1 = new Thread(t1);
		Thread th2 = new Thread(t2);
		th1.start();
		th2.start();
		

	}

}
