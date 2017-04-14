package ch10;

import java.util.HashMap;
public class Q10_6 {	
	public static Q10_6Line findBestLine(GraphPoint[] points) {
		Q10_6Line bestLine = null;
		HashMap<Q10_6Line, Integer> line_count = 
			new HashMap<Q10_6Line, Integer>();
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				Q10_6Line line = new Q10_6Line(points[i], points[j]);
				if (!line_count.containsKey(line)) {
					line_count.put(line, 0);
				}
				line_count.put(line, line_count.get(line) + 1); 
				if (bestLine == null || 
					line_count.get(line) > line_count.get(bestLine)) {
					bestLine = line;
				}
			}
		} 
		return bestLine;
	}	
	
	public static void main(String[] args) {
		int graph_size = 100;
		int n_points = 500;
		GraphPoint[] points = new GraphPoint[n_points];
		for (int i = 0; i < n_points; i++) {
			double x = ((double)(Math.random() * graph_size));
			double y = ((double)(Math.random() * graph_size));
			points[i] = new GraphPoint(x, y);
		}
		Q10_6Line line = findBestLine(points);
		line.Print();
	}
}
