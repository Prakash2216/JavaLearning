package com.javalearning.Reflection;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GetClassesByDifferentWays
{

	public static void main(String[] args) 
	{
		byte [] c = "@$name".getBytes(StandardCharsets.ISO_8859_1);
		String name = Arrays.toString(c);
		System.out.println(name);
		
		String str = new String(c);
		System.out.println(str);
		
		// getting the class using the instance of the class
		Class cls = "str".getClass();
		System.out.println("cls "+cls);
		
		// get the class using the type e.g boolean is primitive type its returns the boolean class
		Class tcls = boolean.class;
		System.out.println("Class using type : "+ tcls);
		
		//Another way of getting the class for primitive type is to using "Wrapper_Class.TYPE" every wrapper class has TYPE variable for boxing. 
		Class tcls1 = Void.TYPE;
		System.out.println("tcls1 "+tcls1);
		
		Class tcls2 = void.class;
		System.out.println("tcls2 "+tcls2);
		
		Class cString = String[][].class;
		System.out.println(cString);
		
		Class cDoubleArray = double[].class;
		System.out.println(cDoubleArray);
		
		Class gc = boolean.class;
		Class gcc = gc.getSuperclass();
		System.out.println(gc);
				
	}

}
