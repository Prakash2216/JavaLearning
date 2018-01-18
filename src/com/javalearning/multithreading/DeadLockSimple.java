package com.javalearning.multithreading;

class MyRunnable1 implements Runnable
{
	@Override
	public void run()
	{
			synchronized(String.class) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				synchronized(Object.class)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
}

class MyRunnable2 implements Runnable
{
	@Override
	public void run()
	{
		synchronized(Object.class)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(String.class)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
public class DeadLockSimple {

	public static void main(String[] args) {
		
		MyRunnable1 m1 = new MyRunnable1();
		MyRunnable2 m2 = new MyRunnable2();
		
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		
		t1.start();
		t2.start();
	}

}
