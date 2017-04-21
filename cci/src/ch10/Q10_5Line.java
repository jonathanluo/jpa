package ch10;

public class Q10_5Line {
	public Point start;
	public Point end;
	public Q10_5Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		return "Line from " + start.toString() + " to " + end.toString();
	}
}
