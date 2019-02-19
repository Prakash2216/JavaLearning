package com.javalearning.ImmutableClasses;

//User Mutable class
class User implements Cloneable
{
	private String name;
	private int age;
	
	User(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	
	public Object clone()
	{
		try {
			return super.clone();
		}
		catch(CloneNotSupportedException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
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

//Immutable class

final class DiskDriveInfo
{
	private final int disksize;
	private final String volumeLabel;
	private final User driveShare;
	
	DiskDriveInfo(int disksize, String volumeLabel, User share)
	{
		this.disksize = disksize;
		this.volumeLabel = volumeLabel;
		this.driveShare = (User)share.clone();
	}
	
	public int getDiskSize()
	{
		return disksize;
	}
	
	public String getVolueLabel()
	{
		return volumeLabel;
	}
	public User getShare()
	{
		//User newShare = new User(driveShare.getName(), driveShare.getAge());
		return (User)driveShare.clone();
	}
}

public class ImmutableDriver 
{

	public static void main(String[] args) 
	{
		User share1 = new User("Suresh", 29);
		DiskDriveInfo ddInfo = new DiskDriveInfo(100, "MyDrive", share1);
		
		//share1.clone();
		User share2 = ddInfo.getShare();
		System.out.println("User name from share2 : "+share2.getName());
		
		share1.setName("Prakash");
		System.out.println("User name from share2 : "+share2.getName());
	}

}

/*
 * Output1 :
 * 
 * User name from share2 : Suresh
 * User name from share2 : Prakash
 */
/*
 * This DiskDriveInfo class is not immutable. since the value of user class member we are able to change for the same object of
 * DiskDriveInfo class.
 * 
 * After making changes to the existing code by cloning the object in constructor and the get method of mutable object.
 * 
 * 
 */ 
