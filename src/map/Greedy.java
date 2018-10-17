package map;

public class Greedy extends GenericSearch {
	@Override
	public double FCostFucntion(int pathLength, double hValue) {
		return hValue;
	}
}
