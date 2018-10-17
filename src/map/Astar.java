package map;

public class Astar extends GenericSearch {
	@Override
	public double FCostFucntion(int pathLength, double hValue) {
		return pathLength+hValue;
	}
}
