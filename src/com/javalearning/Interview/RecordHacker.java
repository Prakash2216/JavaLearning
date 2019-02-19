package com.javalearning.Interview;

public class RecordHacker 
{
    static int solve(int n, int[] s, int d, int m)
    {
        int sum = 0;
        int count=0;
        int j=0;
        int i=0;
        
        if((n < 1 && n > 100) || (d > 31 && d < 1) || (m > 12 && m < 1))
            return 0;
        
        for(j=0; j<m; j++)
            sum += s[j];
        
        if(m == n && sum == d)
            count++;
            
        while( j < n)
        {
               if( sum == d)
                    count++;
                sum -= s[i++];
                sum += s[j++];
            
        }
        if(sum == d)
        	count++;
        return count;
    }

	
	public static void main(String[] args) 
	{
	
		int [] s = {2, 3, 4, 4, 2, 1, 2, 5, 3, 4, 4, 3, 4, 1, 3, 5, 4, 5, 3, 1, 1, 5, 4, 3, 5, 3, 5, 3, 4, 4, 2, 4, 5, 2, 3, 2, 5, 3, 4, 2, 4, 3, 3, 4, 3, 5, 2, 5, 1, 3, 1, 4, 2, 2, 4, 3, 3, 3, 3, 4, 1, 1, 4, 3, 1, 5, 2, 5, 1, 3, 5, 4, 3, 3, 1, 5, 3, 3, 3, 4, 5, 2};
		System.out.println(solve(82, s, 26, 8));
	}

}
