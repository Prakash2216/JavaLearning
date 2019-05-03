package com.javalearning.Interview;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SortMapByKeys {

	public static void main(String[] args) {
		
		Map<Integer, String> name = new HashMap<Integer, String>();
		name.put(3, "Prakash");
		name.put(4, "Suresh");
		name.put(1, "Vimal");
		name.put(2, "Amith");
		
		Map<Integer, String> map = sortByKeys(name);
		System.out.println("Sorted Keys : "+map);
	}

	private static <K extends Comparable, V extends Comparable>Map<K, V> sortByKeys(Map<K, V> name) 
	{
		List<K> keys	= new LinkedList<K>(name.keySet());
		
		Collections.sort(keys);
		
		Map<K, V> sortedKeys = new LinkedHashMap<K, V>();
		for(K key : keys)
		{
			sortedKeys.put(key, name.get(key));
		}
		return sortedKeys;
	}

}
