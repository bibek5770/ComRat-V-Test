package BackEnd;

import java.util.Map;
import java.util.TreeMap;

public class solutionNode {
	public String firstItem, secondItem, thirdItem, correctAnswer;
	private Map<String, Integer> intersectionSpace2;
	private Map<String, Integer> intersectionSpace3;
	private Map<String, Integer> convergedAnswer;
	private Map<String, Integer> firstItemMap;
	private Map<String, Integer> secondItemMap;
	private Map<String, Integer> thirdItemMap;
	private Map<String, frequencyAndLikelyHoodModel> getFrequencyAndLikelyHood;
	private int isCorrect;

	public solutionNode() {
		firstItem = "";
		secondItem = "";
		thirdItem = "";
		correctAnswer = "";
		intersectionSpace2 = new TreeMap<String, Integer>();
		intersectionSpace3 = new TreeMap<String, Integer>();
		convergedAnswer = new TreeMap<String, Integer>();
		firstItemMap = new TreeMap<String, Integer>();
		secondItemMap = new TreeMap<String, Integer>();
		thirdItemMap = new TreeMap<String, Integer>();
		isCorrect = 0;
		getFrequencyAndLikelyHood = new TreeMap<String, frequencyAndLikelyHoodModel>();
	}

	public synchronized void setQuery(String[] query) {
		this.firstItem = query[0];
		this.secondItem = query[1];
		this.thirdItem = query[2];
		this.correctAnswer = query[3];
	}

	public Map<String, Integer> getIntersectionSpace2() {
		return intersectionSpace2;
	}

	public synchronized void setIntersectionSpace2(
			Map<String, Integer> intersectionSpace2) {
		this.intersectionSpace2 = new TreeMap<String, Integer>();
		this.intersectionSpace2.putAll(intersectionSpace2);
	}

	public Map<String, Integer> getIntersectionSpace3() {
		return intersectionSpace3;
	}

	public synchronized void setIntersectionSpace3(
			Map<String, Integer> intersectionSpace3) {
		this.intersectionSpace3 = new TreeMap<String, Integer>();
		this.intersectionSpace3.putAll(intersectionSpace3);
	}

	public Map<String, Integer> getConvergedAnswer() {
		return convergedAnswer;
	}

	public synchronized void setConvergedAnswer(
			Map<String, Integer> convergedAnswer) {
		this.convergedAnswer = new TreeMap<String, Integer>();
		this.convergedAnswer.putAll(convergedAnswer);
	}

	public Map<String, Integer> getFirstItemMap() {
		return firstItemMap;
	}

	public synchronized void setFirstItemMap(Map<String, Integer> firstItemMap) {
		this.firstItemMap = new TreeMap<String, Integer>();
		this.firstItemMap.putAll(firstItemMap);
	}

	public Map<String, Integer> getSecondItemMap() {
		return secondItemMap;
	}

	public synchronized void setSecondItemMap(Map<String, Integer> secondItemMap) {
		this.secondItemMap = new TreeMap<String, Integer>();
		this.secondItemMap.putAll(secondItemMap);
	}

	public Map<String, Integer> getThirdItemMap() {
		return thirdItemMap;
	}

	public synchronized void setThirdItemMap(Map<String, Integer> thirdItemMap) {
		this.thirdItemMap = new TreeMap<String, Integer>();
		this.thirdItemMap.putAll(thirdItemMap);
	}

	public int getCorrect() {
		return isCorrect;
	}

	public synchronized void setCorrect(int correct) {
		this.isCorrect = correct;
	}

	public Map<String, frequencyAndLikelyHoodModel> getFrequencyAndLikelyHood() {
		return this.getFrequencyAndLikelyHood;
	}

	public void calcProbalitiy(databaseTask database) {
		frequencyAndLikelyHoodModel fAndLikelyHood;
		/******************************************************************************************************/
		for (String answ : this.getConvergedAnswer().keySet()) {
			fAndLikelyHood = new frequencyAndLikelyHoodModel();
			if (!(this.getFirstItemMap().get(answ) == null)) {
				fAndLikelyHood.firstItemComratVAnswerFrequency = this
						.getFirstItemMap().get(answ);
				fAndLikelyHood.firstItemFrequency = database.getWordCount(this
						.firstItem);

			}
			/******************************************************************************************************/
			if (!(this.getSecondItemMap().get(answ) == null)) {

				fAndLikelyHood.secondItemComratVAnswerFrequency = this
						.getSecondItemMap().get(answ);
				fAndLikelyHood.secondItemFrequency = database.getWordCount(this
						.secondItem);
			}
			/******************************************************************************************************/
			if (!(this.getThirdItemMap().get(answ) == null)) {
				/************************************/
				fAndLikelyHood.thirdItemComratVAnswerFrequency = this
						.getThirdItemMap().get(answ);
				fAndLikelyHood.thirdItemFrequency = database.getWordCount(this
						.thirdItem);
			}
			/******************************************************************************************************/

			fAndLikelyHood.calculateTotalLikeyHood();
			getFrequencyAndLikelyHood.put(answ, fAndLikelyHood);
		}
		/******************************************************************************************************/
	}
}
