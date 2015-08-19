package BackEnd;

import java.util.ArrayList;
import java.util.TreeMap;

import textFile.ReadWrite;
import excel.ExcelFile;

public class VratAlgorythm {
	private databaseTask database;
	public ReadWrite textFile;
	private Solution solution;
	public ExcelFile excel ;

	public VratAlgorythm() {
		database = new databaseTask();
		textFile = new ReadWrite();
		solution = new Solution();
		excel= new ExcelFile();
	}

	public void setDatabaseFileSource(String databaseSource) {
		database.setDatabaseSource(databaseSource);
		database.testOrCreateConnection();
	}
	
	public Solution solve(String[] incomingQuery) {
		solution=new Solution();
		// databaseTask database = new databaseTask();
		// database.setDatabase("ComratV.db");
		ArrayList<String[]> queries = new ArrayList<String[]>();
		if (incomingQuery == null) {
			queries = textFile.readQueries();
			if (queries == null) {
				System.out
						.println("Invalid file>>Please check the extension and content of the given file");
				return null;
			}
		} else {
			queries.add(incomingQuery);
		}
		// ThreadGroup tg = new ThreadGroup ("subgroup 1");
		for (String[] query : queries) {
			Thread t = new Thread(new solveInnerClass(query));
			t.start();
			// }
			/*
			 * try { t.join(); } catch (InterruptedException e) { // TODO
				e.printStackTrace(); }
			 */
			// System.out.println(Solution);
		}
		return solution;
	}

	public class solveInnerClass implements Runnable {
		String query[];
		boolean flag = false;

		public solveInnerClass(String[] query) {
			this.query = query;
		}

		@Override
		public void run() {
			solutionNode currentSolutionNode = new solutionNode();
			converge findConvergence = new converge();
			// ///
			currentSolutionNode.setQuery(query);
			currentSolutionNode.setFirstItemMap(database.getMatch(query[0]));
			// *********************************************//
			currentSolutionNode.setSecondItemMap(database.getMatch(query[1]));
			// ***********************************************//
			currentSolutionNode.setThirdItemMap(database.getMatch(query[2]));
			// *********************************************//
			try {
				currentSolutionNode.getSecondItemMap().remove(
						query[0].toLowerCase());
				currentSolutionNode.getSecondItemMap().remove(
						query[2].toLowerCase());
				currentSolutionNode.getFirstItemMap().remove(
						query[1].toLowerCase());
				currentSolutionNode.getFirstItemMap().remove(
						query[2].toLowerCase());
				currentSolutionNode.getThirdItemMap().remove(
						query[0].toLowerCase());
				currentSolutionNode.getThirdItemMap().remove(
						query[1].toLowerCase());
			} catch (NullPointerException e) {
				// System.out.println("Null pointer Exception, item not found in map");
				// do nothing
			}
			// ***********************************************//
			ArrayList<TreeMap<String, Integer>> answer = findConvergence
					.get23SpaceIntersection(
							currentSolutionNode.getFirstItemMap(),
							currentSolutionNode.getSecondItemMap(),
							currentSolutionNode.getThirdItemMap());
			currentSolutionNode.setIntersectionSpace2(answer.get(0));
			currentSolutionNode.setIntersectionSpace3(answer.get(1));
			if (answer.get(2).isEmpty() == false) {
				currentSolutionNode.setConvergedAnswer(answer.get(0));
				currentSolutionNode.calcProbalitiy(database);
				currentSolutionNode.setConvergedAnswer(answer.get(1));
				currentSolutionNode.calcProbalitiy(database);
				currentSolutionNode.setConvergedAnswer(answer.get(2));
				currentSolutionNode.calcProbalitiy(database);
				for (String ans : currentSolutionNode.getConvergedAnswer()
						.keySet()) {
					if (currentSolutionNode.correctAnswer.contains(ans)) {
						solution.addCorrectAnswer();
						currentSolutionNode.setCorrect(1);
						flag = true;
					}
				}
				if (flag == false) {
					currentSolutionNode.setCorrect(-1);
				}
			}
			solution.add(currentSolutionNode);
		}
	}
}
