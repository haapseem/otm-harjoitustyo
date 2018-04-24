package com.otm.motor.path;

import java.util.ArrayList;

import com.otm.motor.Velocity;

public class Path implements Comparable<Path> {

	private ArrayList<PathPoint> pPoints;
	Velocity v;

	public Path(Velocity v) {
		this.pPoints = new ArrayList<PathPoint>();
		this.v = v;
	}

	public void addPoint(PathPoint p) {
		this.pPoints.add(p);
	}

	public ArrayList<PathPoint> getPathPoints() {
		return this.pPoints;
	}

	public int getValue() {
		int x = this.pPoints.size();
		x += pPoints.get(pPoints.size() - 1).getH();
		return x;
	}

	public Velocity getVelocity() {
		return v;
	}

	public void addAll(ArrayList<PathPoint> pathPoints) {
		this.pPoints.addAll(pathPoints);
	}

	public void setVelocity(Velocity v) {
		this.v = v;
	}

	public int getX() {
		return this.pPoints.get(this.pPoints.size() - 1).getX();
	}

	public int getLastX() {
		return this.pPoints.get(this.pPoints.size() - 2).getX();
	}

	public int getY() {
		return this.pPoints.get(this.pPoints.size() - 1).getY();
	}

	public int getLastY() {
		return this.pPoints.get(this.pPoints.size() - 2).getY();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pPoints == null) ? 0 : pPoints.hashCode());
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
		Path other = (Path) obj;
		if (pPoints == null) {
			if (other.pPoints != null)
				return false;
		} else if (!pPoints.equals(other.pPoints))
			return false;
		return true;
	}

	public int compareTo(Path p) {
		/*
		 * 
		 * 
		 * int x = 0; int y = 0; for (PathPoint point : this.pPoints) { x +=
		 * point.getG(); } x += pPoints.get(pPoints.size() - 1).getH(); for (PathPoint
		 * point : p.getPathPoints()) { y += point.getG(); } y +=
		 * p.getPathPoints().get(pPoints.size() - 1).getH();
		 */
		return this.getValue() - p.getValue();
		// return this.getH() - p.getH();
	}

	public int getH() {
		return this.getPathPoints().get(this.getPathPoints().size() - 1).getH();
	}

	public String toString() {
		String s = "\n{" + this.getPathPoints().get(this.getPathPoints().size() - 1).getH() + ", \nvelocity: " + this.v
				+ ", \npoints: { \n";
		for (PathPoint p : this.pPoints) {
			s += "\t [" + p.toString() + "] \n";
		}
		s += "}\n";
		return s;
	}
}
