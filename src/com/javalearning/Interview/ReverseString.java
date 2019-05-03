package com.javalearning.Interview;

public class ReverseString 
{
	public String reverseString(String s)
	{
		if(s == null || s.isEmpty())
			return null;
		
		s = s.trim();
		char [] carr = s.toCharArray();
		int len = carr.length;
		
		removeWhiteSpaces(carr, len);
		
		reverse(carr, 0, len-1);
		
		int si=0;
		int i;
		for(i =0; i<len; i++) 
		{
			if(carr[i] == ' ')
			{
				reverse(carr, si, i-1);
				si = i+1;
			}				
		}		
		reverse(carr, si, i-1);
			
		return String.valueOf(carr);
	}
	
	private void removeWhiteSpaces(char[] carr, int len) 
	{		
		int j=0;
		
		for(int i=1; i<len; i++)
		{
			if(carr[i] !=' ' && carr[j] == ' ')
			{
				carr[j] = carr[i];
				j++;
			}
		}
		
	}

	private void reverse(char [] carr, int startIndex, int endIndex) 
	{
		for(int i=startIndex, j= endIndex; i<j; i++, j--)
		{
			char temp = carr[i];
			carr[i] = carr[j];
			carr[j] = temp;			
		}
	}
	
	public static void main(String[] args) 
	{
		String str ="  the sky is blue ";
		ReverseString obj = new ReverseString();
		String rStr = obj.reverseString(str);
		System.out.println(rStr);
	}

}
