package map;

public class Dynamic extends GenericSearch {
	@Override
	public double FCostFucntion(int pathLength, double hValue) {
		return pathLength;
	}
}
