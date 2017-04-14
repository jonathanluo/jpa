package ch10;

public class Square {
	public double left;
	public double top;
	public double bottom;
	public double right;
	public Square(double left, double top, double size) {
		this.left = left;
		this.top = top;
		this.bottom = top + size;
		this.right = left + size;
	}
	
	public Point middle() {
		return new Point((this.left + this.right)/2, (this.top + this.bottom)/2);
	}
	
	public Q10_5Line cut(Square other) {
		Point middle_s = this.middle();
		Point middle_t = other.middle();
		if (middle_s == middle_t) {
			return new Q10_5Line(new Point(left, top), new Point(right, bottom));
		} else {
			return new Q10_5Line(middle_s, middle_t);
		}
	}
	
	public String toString() {
		return "(" + left + ", " + top + ")|(" + right + "," + bottom + ")";
	}
}
