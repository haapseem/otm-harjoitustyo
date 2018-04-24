package com.otm.motor.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import com.otm.motor.Map;
import com.otm.motor.Velocity;

public class PathFinder {
	protected static Logger logger = Logger.getLogger(PathFinder.class.getName());

	private int x, y, x2, y2;
	private Map map;

	public PathFinder(int x, int y, int x2, int y2, Map map) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.map = map;
	}

	public void changeXsAndYs(int x, int x2, int y, int y2) {

		logger.info(x + ", " + y + ", " + x2 + ", " + y2);
		this.x = x;
		this.x2 = x2;
		this.y = y;
		this.y2 = y2;
	}

	int loop = 0;

	public ArrayList<Path> getPath() {
		ArrayList<Path> paths = new ArrayList<Path>();

		while (true) {
			if (paths.isEmpty()) {
				if (this.map.getMap()[y - 1][x] == 0 || this.map.getMap()[y - 1][x] == 2) {
					Path p = new Path(Velocity.UP);
					p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - y)), y, x));
					p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - (y - 1))), y - 1, x));
					paths.add(p);
				}
				if (this.map.getMap()[y + 1][x] == 0 || this.map.getMap()[y + 1][x] == 2) {
					Path p = new Path(Velocity.DOWN);
					p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - y)), y, x));
					p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - (y + 1))), y + 1, x));
					paths.add(p);
				}
				if (this.map.getMap()[y][x - 1] == 0 || this.map.getMap()[y][x - 1] == 2) {
					Path p = new Path(Velocity.LEFT);
					p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - y)), y, x));
					p.addPoint(new PathPoint((Math.abs(this.x2 - (this.x - 1)) + Math.abs(this.y2 - y)), y, x - 1));
					paths.add(p);
				}
				if (this.map.getMap()[y][x + 1] == 0 || this.map.getMap()[y][x + 1] == 2) {
					Path p = new Path(Velocity.RIGHT);
					p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - y)), y, x));
					p.addPoint(new PathPoint((Math.abs(this.x2 - (this.x + 1)) + Math.abs(this.y2 - y)), y, x + 1));
					paths.add(p);
				}
			} else {

				Path path = paths.get(0);

				PathPoint ppu = new PathPoint((Math.abs(this.x2 - path.getX()) + Math.abs(this.y2 - (path.getY() - 1))),
						path.getY() - 1, path.getX());
				PathPoint ppd = new PathPoint((Math.abs(this.x2 - path.getX()) + Math.abs(this.y2 - (path.getY() + 1))),
						path.getY() + 1, path.getX());
				PathPoint ppl = new PathPoint((Math.abs(this.x2 - (path.getX() - 1)) + Math.abs(this.y2 - path.getY())),
						path.getY(), path.getX() - 1);
				PathPoint ppr = new PathPoint((Math.abs(this.x2 - (path.getX() + 1)) + Math.abs(this.y2 - path.getY())),
						path.getY(), path.getX() + 1);

				if (this.map.getMap()[path.getY() - 1][path.getX()] != 1 && !path.getPathPoints().contains(ppu)) {

					Path p = new Path(path.getVelocity());
					p.addAll(path.getPathPoints());
					p.addPoint(ppu);
					paths.add(p);
				}
				if (this.map.getMap()[path.getY() + 1][path.getX()] != 1 && !path.getPathPoints().contains(ppd)) {

					Path p = path;
					p.addPoint(ppd);
					paths.add(p);
				}
				if (path.getX() != 0 && this.map.getMap()[path.getY()][path.getX() - 1] != 1
						&& !path.getPathPoints().contains(ppl)) {

					Path p = path;
					p.addPoint(ppl);
					paths.add(p);
				}
				if (path.getX() != this.map.getMap()[0].length - 1
						&& this.map.getMap()[path.getY()][path.getX() + 1] != 1
						&& !path.getPathPoints().contains(ppr)) {

					Path p = path;
					p.addPoint(ppr);
					paths.add(p);
				}
				paths.remove(path);

				// Collections.sort(paths);
				// logger.info(paths.toString());

				// break;
			}

			Collections.sort(paths);

			// logger.info(paths.toString());

			//
			// logger.info("" + (paths.get(0).getX() == this.x2 && paths.get(0).getY() ==
			// this.y2));
			// logger.info(paths.get(0).getX() + " = " + this.x2);
			// logger.info(paths.get(0).getY() + " = " + this.y2);

			// logger.info("finding path");
			if (paths.isEmpty()) {
				break;
			}

			if (paths.get(0).getX() == this.x2 && paths.get(0).getY() == this.y2) {
				// logger.info("A HIT");
				break;
			}
			// if (loop == 500) {
			// loop = 0;
			// break;
			// }
			// break;
			loop++;
		}
		if (paths.isEmpty()) {

			Path p = new Path(Velocity.UP);
			p.addPoint(new PathPoint((Math.abs(this.x2 - this.x) + Math.abs(this.y2 - y)), y, x));
			paths.add(p);

			// return p.getPathPoints();
		}
		// return paths.get(0).getPathPoints();
		return paths;
	}

}
