package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Graph {
	public HashMap<String,Node> Plot;
	
	public Graph() {
		Plot = new HashMap<>();
	}
	
	public void AddEdgeToGraph(String n1, String n2, int len) {
		Node n;
		n = Plot.get(n1);
		if( (n = Plot.get(n1)) == null ) {
			n = new Node(n1);
		}
		n.Edges.add(new Edge(n2,len));
		Plot.put(n1, n);
	}
	
	public void AddPosition(String nodeName, float lat, float lon) {
		Node n = Plot.get(nodeName);
		n.SetPosition(lat, lon);
	}
	
	public void SetHeuristic(String destName) {
		Node destNode = Plot.get(destName);
		Set<String> keys = Plot.keySet();
		Iterator<String> keyIterator = keys.iterator();
		while(keyIterator.hasNext()) {
			Node n = Plot.get(keyIterator.next());
			n.CalculateHeuristic(destNode);
		}
	}
}
