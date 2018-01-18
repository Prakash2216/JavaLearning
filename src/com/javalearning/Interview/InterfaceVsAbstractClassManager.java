package com.javalearning.Interview;
/**
 * Q. What value of i will get printed?
 * Ans. None. It will show a the field is ambiguous.Because two copy of i's are present in the class which is extending and implementing.
 * The both abstract class and interface.
 * @author 1019270
 *
 */
interface A
{
	int i=20;
}

abstract class B
{
	int i=10;
}

public class InterfaceVsAbstractClassManager extends B implements A{

	public static void main(String[] args) 
	{
		//System.out.println("i :  "+i);
	}

}
