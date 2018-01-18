package com.javalearning.Interview;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortMapByValues {

	public static void main(String[] args) 
	{		
		Map<Integer, String> name = new HashMap<Integer, String>();
		
		name.put(3, "Prakash");
		name.put(4, "Suresh");
		name.put(1, "Vimal");
		name.put(2, "Amith");
		
		Map<Integer, String> sortedMap = sortByValue(name);
		System.out.println("Sorted map by value "+sortedMap);
	}
	
	private static <K extends Comparable, V extends Comparable> Map<K, V> sortByValue(Map<K, V> name)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<>(name.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() 
		{
			
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				return o1.getValue().compareTo(o2.getValue());
			}			
		});
		
		Map<K, V> sortedValue = new LinkedHashMap<>();
		
		for(Entry<K, V> entry : entries)
		{
			sortedValue.put(entry.getKey(), entry.getValue());
		}
		return sortedValue;		
	}

}
