package BackEnd;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class converge {

	public ArrayList<TreeMap<String, Integer>> get23SpaceIntersection(Map<String, Integer> map1,
		Map<String, Integer> map2, Map<String, Integer> map3) {
		TreeMap<String, Integer> insersectionMap3Space = new TreeMap<String, Integer>();
		TreeMap<String, Integer> intersectionMap2Space = new TreeMap<String, Integer>();
		String word = "";
		
		for (Entry<String, Integer> entry2 : map2.entrySet()) {
			word = entry2.getKey();
			if (map3.containsKey(word)) {
				intersectionMap2Space.put(word,
						entry2.getValue() + map3.get(word));
			}
		}
		
		for (Entry<String, Integer> entry1 : map1.entrySet()) {
			word = entry1.getKey();
			if (map2.containsKey(word)) {
				if (map3.containsKey(word)) {
					// all three intersect
					insersectionMap3Space
							.put(word, entry1.getValue() + map2.get(word)
									+ map3.get(word));
					intersectionMap2Space.remove(word);
				} else {
					intersectionMap2Space.put(word,
							entry1.getValue() + map2.get(word));
				}
			} else if (map3.containsKey(word)) {
				intersectionMap2Space.put(word,
						entry1.getValue() + map3.get(word));
			}
		}	
		
		Map<String, Integer>returnAns= new TreeMap<String,Integer>();
		if (insersectionMap3Space.isEmpty() == false) {
			returnAns=getFinalAnswers(insersectionMap3Space);
		} else if (intersectionMap2Space.isEmpty() == false) {
			returnAns=getFinalAnswers(intersectionMap2Space);
		}
		ArrayList<TreeMap<String, Integer>> maps = new ArrayList<TreeMap<String,Integer>>(3); 
		maps.add(intersectionMap2Space);
		maps.add(insersectionMap3Space);
		maps.add((TreeMap<String, Integer>) returnAns);
		return maps;
	}

	private static  Map<String,Integer> getFinalAnswers(Map<String, Integer> map) {
		TreeMap<String, Integer> finalMap = null;//
		int frequency = 0;
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > frequency) {
				finalMap = new TreeMap<String, Integer>();
				finalMap.put(entry.getKey(), entry.getValue());
				frequency = entry.getValue();
			} else if (entry.getValue() == frequency) {
				finalMap.put(entry.getKey(), frequency);
			}
		}
		return finalMap;
	}
}
