package map;

import java.util.ArrayList;

public class Node {
	public String Name;
	public float Lat;
	public float Lon;
	public ArrayList<Edge> Edges;
	public Node Parent;
	public double Heuristic;
	public double Cost;
	public int PathLength;
	
	public Node(String name) {
		this.Name = name;
		Edges = new ArrayList<Edge>();
	}
	
	public void SetPosition(float lat, float lon) {
		Lat = lat;
		Lon = lon;
	}
	
	public void CalculateHeuristic(Node dest) {
		this.Heuristic = Math.sqrt( 
					Math.pow( 69.5*(dest.Lat-this.Lat) ,2)+
					Math.pow( 69.5*Math.cos( (dest.Lat+this.Lat)/360 * Math.PI )*(dest.Lon-this.Lon) , 2)
				);
	}
/*	public void CalculateHeuristic(Node dest) {
		this.Heuristic = Math.sqrt( 
					Math.pow(dest.Lat-this.Lat ,2)+Math.pow(dest.Lon-this.Lon, 2)
				);
	}*/
}
