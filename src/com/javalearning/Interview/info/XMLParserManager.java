package com.javalearning.Interview.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <!Entity a1 10>
 * <!Entity a2	a1;a1>
 *
 * @author 1019270
 *
 */
public class XMLParserManager 
{
	private static List<String> entities;
	
	public XMLParserManager()
	{
		entities = new ArrayList<String>();
	}
	
	public void add()
	{
		entities.add("<!Entity a1 a2;a1>");
		entities.add("<!Entity a2 a1>");
		entities.add("<!Entity a1 20>");
	}
	
	public void xmlExplosionDetection(int threshold, List<String> entities)
	{
		int count =0;
		String [] strs;
		Map<String, Integer> lookup = new HashMap<>();
		Integer value;
		for(String entity : entities )
		{			
			strs = entity.substring(2, entity.length()-1).split(" ");
			
			if(!strs[2].contains(";"))
			{
				value = lookup.get(strs[1]);
				
				if(value == null)
					lookup.put(strs[1], 1);
				else
					lookup.put(strs[1], value+1);
			}
			else
			{
				String [] temp = strs[2].split(";");
				
				for(String s : temp)
				{
					value = lookup.get(s);
					
					if(value != null)
						lookup.put(s, value+1);
					else
						lookup.put(s, 1);
				}
			}			
		}
		
		for(Map.Entry<String, Integer> entry : lookup.entrySet())
		{
			count += entry.getValue();
		}
		
		if(count < threshold)
			System.out.println(1+" "+count);
		else
			System.out.println(0+" "+count);
	}	
	
	public static void main(String[] args) 
	{
		XMLParserManager obj = new XMLParserManager();
		obj.add();
		obj.xmlExplosionDetection(5, entities);
	}

}
