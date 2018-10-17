package map;

public interface Search {
	Node GraphSearch(Graph g, String source, String destination);
	String[] SolutionState();
	String[] SolutionPath(Node n);
}
