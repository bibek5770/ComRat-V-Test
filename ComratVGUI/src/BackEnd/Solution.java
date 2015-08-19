package BackEnd;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Solution {
	private Map<Integer, solutionNode> map;
	private int size;
	private int noCorrectAnswer;

	public Solution() {
		map = new TreeMap<Integer, solutionNode>();
		size = 0;
		noCorrectAnswer = 0;
	}

	public int getSize() {
		return size;
	}

	public synchronized void add(solutionNode node) {
		map.put(size + 1, node);
		size++;
	}

	public solutionNode getCurrentNode() {
		return map.get(size);
	}

	public Map<Integer, solutionNode> getSolution() {
		return map;
	}

	public int getCorrectAnswer() {
		return noCorrectAnswer;
	}

	public synchronized void addCorrectAnswer() {
		++noCorrectAnswer;
	}

	public Map<Integer, solutionNode> toExcel() {
		return this.getSolution();
	}
	
	@Override
	public String toString() {
		solutionNode currentSolutionNode;
		String n = System.lineSeparator();
		StringBuilder textFileToPrint = new StringBuilder();
		for (Entry<Integer, solutionNode> entry : this.map.entrySet()) {
			currentSolutionNode = entry.getValue();
			textFileToPrint.append(entry.getKey() + "=> ");
			textFileToPrint.append(currentSolutionNode.firstItem + " , " + currentSolutionNode.secondItem + " , "
					+ currentSolutionNode.thirdItem + " , " + currentSolutionNode.correctAnswer.toUpperCase()
					+ n);
			textFileToPrint.append("\tPossible answers from 2 set intersection => "
					+ currentSolutionNode.getIntersectionSpace2() + n);
			textFileToPrint.append("\tPossible answers from 3 set intersection => "
					+ currentSolutionNode.getIntersectionSpace3() + n);
			if (!(currentSolutionNode.getConvergedAnswer().isEmpty())) {
				textFileToPrint.append("vRat Answer :=> " + currentSolutionNode.getConvergedAnswer() + n);
			}
			
			switch(currentSolutionNode.getCorrect()){
			case 1: textFileToPrint.append("!!!!!!!!Correct Answer!!!!"+n);
					break;
			case 0: textFileToPrint.append("!!!!!!!!No Convergence!!!!"+n);
					break;
			case -1: textFileToPrint.append("!!!!!!!!InCorrect Answer!!!!"+n);
					break;
			}			
			for (Entry<String,frequencyAndLikelyHoodModel> answer : currentSolutionNode.getFrequencyAndLikelyHood().entrySet()){
				textFileToPrint.append("\t" + answer.getKey().toUpperCase() + " : " + currentSolutionNode.firstItem
						+ " = " + answer.getValue().firstItemComratVAnswerFrequency+"/"+answer.getValue().firstItemFrequency +
						" = "+answer.getValue().firstItemLikelyhood);
				textFileToPrint.append(
						"   ##  " + currentSolutionNode.secondItem+
						" = " + answer.getValue().secondItemComratVAnswerFrequency+"/"+answer.getValue().secondItemFrequency +
						" = "+answer.getValue().secondItemLikelyhood);
				textFileToPrint.append(
						"   ##  " + currentSolutionNode.thirdItem+ 
						" = " + answer.getValue().thirdItemComratVAnswerFrequency+"/"+answer.getValue().thirdItemFrequency+
						" = "+answer.getValue().thirdItemLikelyhood+
						" ##  TotalLikelyHood = "+answer.getValue().totalLikelyhood+ n);
			}
			textFileToPrint.append("-------------------------------------------------------------------------------------------------------------------------"
					+ n
					+ "-------------------------------------------------------------------------------------------------------------------------"
					+ n);

		}
		textFileToPrint.append(n + "Total Number of Correct Answer: => ");
		textFileToPrint.append(this.getCorrectAnswer() + n);
		textFileToPrint.append("############################################################################################################"
				+ n);
		textFileToPrint.append("############################################################################################################"
				+ n);
		return textFileToPrint.toString();
	}
}
