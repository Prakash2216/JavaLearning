package com.javalearning.ImmutableClasses;

class Author
{
	String name;
	int age;
	
	Author(){}
	
	Author(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}


final class Book
{
	private String bookName;
	private double price;
	private Author auth; // Mutable object
	
	Book(String bookName, double price, Author a)
	{
		this.bookName = bookName;
		this.price = price;
		auth = new Author(a.name, a.age);
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Author getAuth() {
		Author tempAuth = new Author();
		tempAuth.name = auth.name;
		tempAuth.age = auth.age;
		return tempAuth;
	}
	
}
public class ImmutableClassContainsMutableObject {

	public static void main(String[] args) 
	{
		Author a = new Author("Suresh", 29);
		Book b = new Book("ML", 59, a);
		
		Author at = b.getAuth();
		System.out.println("Author : "+at.name);
		at.setName("Prakash"); 
		
		System.out.println(a.getName());
		System.out.println(at.getName());
		
	}

}
