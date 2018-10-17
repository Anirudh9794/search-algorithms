package map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class GenericSearch implements Search {
	
	public class FrontierEntry {
		Double totalCost;
		String nodeName;
		int PathLength;
		double heuristic;
		public FrontierEntry(double totalCost, String nodeName) {
			this.totalCost = totalCost;
			this.nodeName = nodeName;
		}
	};
	
	private class FrotierEntryComparator implements Comparator<FrontierEntry>{
		@Override
		public int compare(FrontierEntry e1, FrontierEntry e2) {
			return e1.totalCost.compareTo(e2.totalCost);
		}
	};
	
	public HashMap<String, FrontierEntry> FrontierMap = new HashMap<>();
	
	public ArrayList<FrontierEntry> FrontierList = new ArrayList<>();
	
	public HashMap<String, Node> Explored = new HashMap<>();
	
	// Original logic for graph search
	public Node GraphSearch(Graph g, String source, String destination) {
		Node initialNode = g.Plot.get(source);
		g.SetHeuristic(destination);
		InsertToFrontier(0.0, 0, initialNode, null);
		
		while(FrontierList.size() > 0 ) {
			String nName = GetFrontierTop().nodeName;
			Node parent = g.Plot.get(nName);
			if(GoalTest(nName, destination)) {
				return parent;
			}
			ArrayList<Edge> moves = MoveGenerator(parent);
			Iterator<Edge> itr = moves.iterator();
			while(itr.hasNext()) {
				Edge ed = itr.next();
				Node child = g.Plot.get(ed.N);
				
				double tempCost = FCostFucntion(parent.PathLength + ed.Length, child.Heuristic);
				
				if(!FrontierMap.containsKey(child.Name) && !Explored.containsKey(child.Name)) {
					InsertToFrontier(tempCost, parent.PathLength + ed.Length, child, parent);
				} else if (FrontierMap.containsKey(child.Name) && child.Cost > tempCost) {
					RemoveFromFrontier(child);
					InsertToFrontier(tempCost, parent.PathLength + ed.Length, child, parent);
				}
			}
			Explored.put(parent.Name, parent);
		}	
		System.out.println("No solution");
		return null;
	}
	
	public boolean GoalTest(String n, String destState) {
		return n.equals(destState);		
	}
	
	public void InsertToFrontier(double totalCost, int pathLength, Node node, Node parent) {
		FrontierEntry fe = new FrontierEntry(totalCost, node.Name);
		FrontierMap.put(node.Name, fe);
		FrontierList.add(fe);
		node.Parent = parent;
		node.PathLength = pathLength;
		node.Cost = totalCost;
		fe.PathLength = pathLength;
		fe.heuristic = node.Heuristic;
	}
	
	public FrontierEntry GetFrontierTop() {
		FrontierList.sort(new FrotierEntryComparator());
		/*Iterator<FrontierEntry> itr = FrontierList.iterator();
		while(itr.hasNext()) {
			FrontierEntry x =itr.next();
			System.out.println(x.nodeName+ "\t"+ x.PathLength + "\t" + x.heuristic+ "\t"+ x.totalCost);
		}
		//System.out.println();*/
		FrontierEntry e = FrontierList.remove(0);
		FrontierMap.remove(e.nodeName);
		return e;
	}
	
	public ArrayList<Edge> MoveGenerator(Node n) {
		return n.Edges;
	}
	
	public String[] SolutionState() {
		return Explored.keySet().toArray(new String[] {});
	}
	
	public String[] SolutionPath(Node node) {
		Node i = node;
		ArrayList<String> solutionList = new ArrayList<String>();
		while( i != null) {
			solutionList.add(0, i.Name);
			i = i.Parent;
		}
		return solutionList.toArray(new String[] {});
	}
	
	public FrontierEntry RemoveFromFrontier(Node node) {
		FrontierEntry fe = FrontierMap.remove(node.Name);
		FrontierList.remove(fe);
		return fe;
	}
	
	public ArrayList<Node> parseSolutionNode(Node n) {
		ArrayList<Node> l = new ArrayList<>();
		Node i = n;
		while( i.Parent != null) {
			l.add(i);
			i = i.Parent;
		}
		return l;
	}
	
	// FCostFunction is overridden in different algorithms
	public double FCostFucntion(int pathLength, double hValue) {
		return 0;
	}
}
