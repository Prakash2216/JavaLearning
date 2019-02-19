package com.javalearning.ImmutableClasses;


class Employee
{
	private int id;
	private String name;
	
	public Employee()
	{}
	
	public Employee(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
}

public class ImmutableManager {

	public static void main(String[] args) {
		
		Employee emp = new Employee(1, "Suresh");
		System.out.println("Emp id : "+emp.getId()+" Name : "+emp.getName());

	}

}
