package com.javalearning.ImmutableClasses;

import java.util.Vector;

/**
 * Here in this example we tried to make the immutable class by using the clone method but we have a collection object.
 * which is also mutable and contains the mutable object in it. when we clone the collection object it gives the shallow copy
 * of the collection object and when we run the program. we get the different value.
 * 
 *There are different ways to make it immutable by using clone method as well as by doing custom cloning :)
 *solution :
 *
 *
 * @author 1019270
 *
 */
// Mutable class
class User1 implements Cloneable
{
	private String name;
	private int age;
	
	User1(String name, int age)
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

final class DiskDriveInfoColl
{
	private final int disksize;
	private final String volumeLabel;
	private final Vector driveShare;
	
	DiskDriveInfoColl(int disksize, String volumeLabel, Vector<User1> share)
	{
		this.disksize = disksize;
		this.volumeLabel = volumeLabel;
		this.driveShare = (Vector)share.clone();
	}
	
	public int getDiskSize()
	{
		return disksize;
	}
	
	public String getVolueLabel()
	{
		return volumeLabel;
	}
	public Vector<User1> getShare()
	{
		//User newShare = new User(driveShare.getName(), driveShare.getAge());
		return (Vector)driveShare.clone();
	}
}

public class ImmutableClassContainsCollectionOfMutableClassObj 
{
	public static void main(String []args)
	{
		User1 share1 = new User1("Suresh", 29);
		User1 share2 = new User1("Suresh1", 30);
		
		Vector<User1> sharedVec = new Vector<>(2);
		sharedVec.add(share1);
		sharedVec.add(share2);
		
		DiskDriveInfoColl obj = new DiskDriveInfoColl(100, "my drive", sharedVec);
		
		Vector<User1> vec = obj.getShare();
		System.out.println("User with shared access are : "+vec.get(0).getName()+", "+vec.get(1).getName());
		
		share1.setName("Prakash");
		System.out.println("User with shared access are : "+vec.get(0).getName()+", "+vec.get(1).getName());
	}
}

