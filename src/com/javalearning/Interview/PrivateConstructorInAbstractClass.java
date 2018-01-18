package com.javalearning.Interview;
/**
 * Q. Can an abstract class have private constructor?
 * Ans. An abstract class can have a private constructor.But abstract class can't be extended by other class.
 * except the inner class. Because constructor chaining can't be completed.
 * @author 1019270
 *
 */

abstract class Base
{
	public abstract void set();
	private Base() 
	{
		System.out.println("This is private constructor of Base class");
	}	
	
	static class Derived extends Base
	{
		public void set()
		{
			System.out.println("Overrided set method");
		}
	}
}
public class PrivateConstructorInAbstractClass{

	public static void main(String[] args) {
		
		Base.Derived obj = new Base.Derived();
		obj.set();
	}

}
