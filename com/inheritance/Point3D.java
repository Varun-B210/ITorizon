package com.inheritance;

public class Point3D extends Point2D{

	private int z;
	
	public Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
		
	}

	public int getZ() {
		return z;
	}

	@Override
	public String toString() {
		String res = "("+super.getX()+", "+super.getY()+", "+z+")";
		return res;
	}


	
	
	
}
