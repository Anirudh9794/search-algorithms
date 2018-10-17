package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class SearchUSA {
	public static String source, destination, algorithm;
	private static final String ASTAR = "astar", DYNAMIC = "dynamic", GREEDY = "greedy";
	public static void main(String[] args) {
		Graph g = USAMap.InitMap();
		/*if (args.length == 0) {
			Experiment3();
			return;
		}*/
		if (args.length != 3) {
			System.out.println("Invalid number of arguments");
			return;
		}
		algorithm = args[0];
		source = args[1];
		destination = args[2];
		if (!isValidSourceDestination(g, source, destination)) {
			System.out.println("Invalid source or destination");
			return;
		}
		Search s = null;
		switch (algorithm) {
			case ASTAR:
				s = new Astar(); /*Experiment1(g);*/ break;
			case GREEDY:
				s = new Greedy(); break;
			case DYNAMIC:
				s = new Dynamic(); break;
			default:
				System.out.printf("Algoritm one of %s, %s, %s\n", ASTAR, GREEDY, DYNAMIC);
				return;
		}
		Node soln  = s.GraphSearch(g, source, destination);
		String[] explored = s.SolutionState();
		String[] path = s.SolutionPath(soln);
		printSolution(soln, path, explored);
    }
	
	public static void Experiment1(Graph g) {
		Set<String> cities = g.Plot.keySet();
		Iterator<String> itr = cities.iterator();
		int count = 0;
		while(itr.hasNext()) {
			Iterator<String> itr2 = cities.iterator();
			String source = itr.next();
			Astar s;
			while( itr2.hasNext()) {
				String dest = itr2.next();
				s = new Astar();
				Graph g1 = USAMap.InitMap();
				Node n1 = s.GraphSearch(g1, source, dest);
				ArrayList<Node> soln1 = s.parseSolutionNode(n1);
				s = new Astar();
				Graph g2 = USAMap.InitMap();
				Node n2 = s.GraphSearch(g2, dest, source);
				ArrayList<Node> soln2 = s.parseSolutionNode(n2);
				if(soln1.size() != soln2.size()) {
					count++;
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln1.size()+ " PathLength: "+ n1.PathLength);
					System.out.println("source: "+dest+" destination: "+source+ " Nodes: "+soln2.size()+ " PathLength: "+ n2.PathLength);
					System.out.println();
				}
			}
		}
		System.out.println("Count = "+count);
	}
	
	public static void Experiment2() {
		Graph g = USAMap.InitMap();
		Set<String> cities = g.Plot.keySet();
		Iterator<String> itr = cities.iterator();
		int PathCount = 0;
		int ExploredCount =0;
		Astar s;
		Greedy s2;
		while(itr.hasNext()) {
			Iterator<String> itr2 = cities.iterator();
			String source = itr.next();
			while( itr2.hasNext()) {
				String dest = itr2.next();
				s = new Astar();
				Graph g1 = USAMap.InitMap();
				Node n1 = s.GraphSearch(g1, source, dest);
				ArrayList<Node> soln1 = s.parseSolutionNode(n1);
				s2 = new Greedy();
				Graph g2 = USAMap.InitMap();
				Node n2 = s2.GraphSearch(g2, source, dest);
				ArrayList<Node> soln2 = s2.parseSolutionNode(n2);
				if(soln1.size() < soln2.size()) {
					PathCount++;
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln1.size()+ " PathLength: "+ n1.PathLength);
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln2.size()+ " PathLength: "+ n2.PathLength);
					System.out.println();
				}
				if(s.Explored.size() < s2.Explored.size()) {
					ExploredCount++;
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln1.size()+ " Explored: "+ s.Explored.size());
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln2.size()+ " Explored: "+ s2.Explored.size());
					System.out.println();
				}
			}
		}
		System.out.printf("PathCount = %d\t ExploredCount =%d\n", PathCount, ExploredCount);
	}
	
	public static void Experiment3() {
		Graph g = USAMap.InitMap();
		Set<String> cities = g.Plot.keySet();
		Iterator<String> itr = cities.iterator();
		int PathCount = 0;
		int ExploredCount =0;
		Astar s;
		Dynamic s2;
		while(itr.hasNext()) {
			Iterator<String> itr2 = cities.iterator();
			String source = itr.next();
			while( itr2.hasNext()) {
				String dest = itr2.next();
				s = new Astar();
				Graph g1 = USAMap.InitMap();
				Node n1 = s.GraphSearch(g1, source, dest);
				ArrayList<Node> soln1 = s.parseSolutionNode(n1);
				s2 = new Dynamic();
				Graph g2 = USAMap.InitMap();
				Node n2 = s2.GraphSearch(g2, source, dest);
				ArrayList<Node> soln2 = s2.parseSolutionNode(n2);
				if(n2.PathLength > n1.PathLength) {
					PathCount++;
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln1.size()+ " PathLength: "+ n1.PathLength);
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln2.size()+ " PathLength: "+ n2.PathLength);
					System.out.println();
				}
				if(s.Explored.size() < s2.Explored.size()) {
					ExploredCount++;
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln1.size()+ " Explored: "+ s.Explored.size());
					System.out.println("source: "+source+" destination: "+dest+ " Nodes: "+soln2.size()+ " Explored: "+ s2.Explored.size());
					System.out.println();
				}
			}
		}
		System.out.printf("PathCount = %d\t ExploredCount =%d\n", PathCount, ExploredCount);
	}
	
	public static void printSolution(Node soln, String[] path, String[] explored) {
		System.out.println("Expanded Nodes:");
		System.out.println("\t"+String.join(",", explored));
		System.out.printf("Number of Expanded nodes: %d\n", explored.length);
		System.out.println("Solution Path:");
		System.out.println("\t"+String.join(",", path));
		System.out.printf("Number of nodes in solution path: %d\n", path.length);
		System.out.printf("Total distance: %d\n", soln.PathLength);
	}
	
	public static boolean isValidSourceDestination(Graph g, String source, String destination) {
		return g.Plot.containsKey(source) && g.Plot.containsKey(destination);
	}
}
