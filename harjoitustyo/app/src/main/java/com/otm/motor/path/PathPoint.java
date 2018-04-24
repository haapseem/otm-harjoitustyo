package com.otm.motor.path;

public class PathPoint {

	// g = route travelled
	// h = distance
	private int g, h, x, y;

	/**
	 * 
	 * @param h
	 * @param y
	 * @param x
	 */
	public PathPoint(int hx, int hy, int y, int x) {
		if (hy < 0) {
			hy = 0 - hy;
		}
		if (hx < 0) {
			hx = 0 - hx;
		}
		this.g = 1;
		this.h = hy + hx;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PathPoint other = (PathPoint) obj;
		if (x != other.getX())
			return false;
		if (y != other.getY())
			return false;
		return true;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return this.g + this.h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String toString() {
		return this.getX() + ", " + this.getY();
	}

}
