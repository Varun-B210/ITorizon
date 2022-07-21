package com.inheritance;

public class Point2D {

	private int x;
	private int y;
	
	public Point2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "("+ x + ", " + y + ")";
	}

}
