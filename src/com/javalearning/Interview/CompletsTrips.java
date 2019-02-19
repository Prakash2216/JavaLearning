package com.javalearning.Interview;

import java.util.Arrays;
import java.util.Scanner;

public class CompletsTrips {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k= scan.nextInt();
		
		int KD[] = new int[k];
		
		for(int i=0; i< k; i++)
			KD[i] = scan.nextInt();
		
		System.out.println("Min no. of trips "+findMinTrips(n, k, KD));

	}

	private static int findMinTrips(int n, int k, int[]KD) 
	{
		int t =0;
		while(true)
		{
			int item =0;
			for(int i=0; i<k; i++)
			{
				item +=(t/KD[i]);
			}
			if(item >= n)
				return t;
			t++;
		}
	}

}
