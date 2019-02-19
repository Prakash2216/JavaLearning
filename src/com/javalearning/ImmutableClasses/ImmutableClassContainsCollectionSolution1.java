package com.javalearning.ImmutableClasses;

import java.util.Vector;

/**
 * In this solution of immutable class which contains the collection (Vector) of mutable objects by clone method.
 * @author 1019270
 *
 */

class User2 implements Cloneable
{
	public String name;
	public String age;
	
	public User2(String name, String age)
	{
		this.name = name;
		this.age = age;
	}
	
	public Object clone()
	{
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}	
	
}

class SharedVector extends Vector
{
	public SharedVector(int size)
	{
		super(size);
	}
	
	public Object clone()
	{
		SharedVector vec = (SharedVector)super.clone();
		int size = size();
		
		for(int i=0; i<size; i++)
		{
			User2 u = (User2)this.get(i);
			vec.setElementAt((User2)u.clone(), i);
		}		
		
		return vec;
	}
}

final class ImmutableCollSolution
{
	private final int diskSize;
	private final String volumeLabel;
	private final SharedVector driveShare;
	
	public ImmutableCollSolution(int diskSize, String volumeLabel, SharedVector driveShare)
	{
		this.diskSize = diskSize;
		this.volumeLabel = volumeLabel;
		this.driveShare = (SharedVector)driveShare.clone();
	}

	public int getDiskSize() {
		return diskSize;
	}

	public String getVolumeLabel() {
		return volumeLabel;
	}

	public SharedVector getDriveShare() {
		return (SharedVector) driveShare.clone();
	}
	
	
}

public class ImmutableClassContainsCollectionSolution1 
{

	public static void main(String[] args) 
	{
		User2 share1 = new User2("Suresh", "29");
		User2 share2 = new User2("Suresh1", "30");
		
		SharedVector shareVec = new SharedVector(2);
		shareVec.add(share1);
		shareVec.add(share2);
		
		ImmutableCollSolution obj = new ImmutableCollSolution(100, "My Drive", shareVec);
		
		SharedVector vec = obj.getDriveShare();
		System.out.println("User with shared access are : "+((User2) (vec.get(0))).getName()+", "+((User2)(vec.get(1))).getName());
		
		share1.setName("Prakash");
		System.out.println("User with shared access are : "+((User2) (vec.get(0))).getName()+", "+((User2)(vec.get(1))).getName());
	}

}
