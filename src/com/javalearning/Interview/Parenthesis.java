package com.javalearning.Interview;

import java.util.Stack;

public class Parenthesis {

static int closingBracePosition(String inputSentence, int openingBraceNum) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<inputSentence.length(); i++)
        {
        	char ch = inputSentence.charAt(i);
        	
        	if(ch == '(' || ch =='{' || ch == '[')
        		stack.push(ch);
        	else if( (!stack.isEmpty()) && ch == ')' || ch == '}' || ch == ']')
        	{
        		if((char)stack.peek() == '(' && ch == ')' || (char)stack.peek() == '{' && ch == '}' || (char)stack.peek() =='[' && ch == ']' )
        			stack.pop();
        		else
        			return -1;
        	}
        	else
        	{
        		if(ch == ')' || ch == '}' || ch == ']')
        			return -1;
        	}        	
        }	
        
        
        if(stack.isEmpty())
        {
        	int openingCounter = 0;
            int openingStackSize = 0;
            int closingBracePosition = 0;
            
            for (int i = 0; i < inputSentence.length(); i++) 
            {
            	char ch = inputSentence.charAt(i);

	   			if (ch == '(' || ch == '{' || ch == '[')
	   			{
	   				stack.push(ch);
	   				openingCounter++;
	   				
	   				if(openingCounter == openingBraceNum)
	   				{
	   					openingStackSize = stack.size();
	   				}
	   			}
	   			else if ((!stack.isEmpty()) && (ch == ')' || ch == '}' || ch == ']')) 
	   			{
	   				if ((char) stack.peek() == '(' && ch == ')'
	   						|| (char) stack.peek() == '{' && ch == '}'
	   						|| (char) stack.peek() == '[' && ch == ']')
	   				{
	   				   if(stack.size() == openingCounter)
	   				   {
	   					   closingBracePosition = i+1;
	   				   }
	   				   stack.pop();
	   				   
	   				}
	   			}
            }
            return closingBracePosition;
        }
        else
        	return -1;

    }
	public static void main(String[] args) 
	{
		String str = "(i want (to write a language parser), would (you) help me)";
		int n =2;
		System.out.println(closingBracePosition(str, n));

	}

}
