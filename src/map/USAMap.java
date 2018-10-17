package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class USAMap {
	public static String MAP_PATH = "usroads.pl";
	public static String ROAD_REGEX = "(\\w+), (\\w+), (\\d+)";
	public static String POSITION_REGEX = "(\\w+),[ ]*(\\d+\\.\\d+),[ ]*(\\d+\\.\\d+)";
	
	//Reads the prolog file
	public static Graph InitMap() {
		File roads = new File(MAP_PATH);
		Pattern roadPattern = Pattern.compile(ROAD_REGEX);
		Pattern positionPattern = Pattern.compile(POSITION_REGEX);
		Graph g = new Graph();
		try {
			BufferedReader br = new BufferedReader(new FileReader(roads));
			String s;
			while( (s = br.readLine()) != null) {
				Matcher roadMatcher = roadPattern.matcher(s);
				Matcher positionMatcher = positionPattern.matcher(s);
				while(roadMatcher.find()) {
					g.AddEdgeToGraph(roadMatcher.group(1), roadMatcher.group(2), Integer.parseInt(roadMatcher.group(3)));
					g.AddEdgeToGraph(roadMatcher.group(2), roadMatcher.group(1), Integer.parseInt(roadMatcher.group(3)));
				}
				while(positionMatcher.find()) {
					g.AddPosition(positionMatcher.group(1), Float.parseFloat(positionMatcher.group(2)), Float.parseFloat(positionMatcher.group(3)));
				}
			}
			br.close();
		} catch(Exception e) {
			System.out.println("Error reading the roads file "+MAP_PATH);
		}
		return g;
	}
}
