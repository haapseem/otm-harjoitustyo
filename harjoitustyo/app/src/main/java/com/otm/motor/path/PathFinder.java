package com.otm.motor.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

	public void changeXsAndYs(int x, int y, int x2, int y2) {

		// logger.info(x + ", " + y + ", " + x2 + ", " + y2);
		this.x = x;
		this.y = y;
		// this.x = 2;
		// this.y = this.map.getMap().length - 2;
		this.x2 = x2;
		this.y2 = y2;
	}

	int loop = 0;

	public ArrayList<Path> getPath() {
		ArrayList<Path> paths = new ArrayList<Path>();

		long time = (new Date()).getTime();

		while ((new Date()).getTime() - time < 100) {
			if (paths.isEmpty()) {
				if (this.map.getMap()[y - 1][x] != 1) {
					Path p = new Path(Velocity.UP);
					p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - y), y, x));
					p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - (y - 1)), y - 1, x));
					paths.add(p);
				}
				if (this.map.getMap()[y + 1][x] != 1) {
					Path p = new Path(Velocity.DOWN);
					p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - y), y, x));
					p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - (y + 1)), y + 1, x));
					paths.add(p);
				}
				if (this.map.getMap()[y][x - 1] != 1) {
					Path p = new Path(Velocity.LEFT);
					p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - y), y, x));
					p.addPoint(new PathPoint(Math.abs(this.x2 - (this.x - 1)), Math.abs(this.y2 - y), y, x - 1));
					paths.add(p);
				}
				if (this.map.getMap()[y][x + 1] != 1) {
					Path p = new Path(Velocity.RIGHT);
					p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - y), y, x));
					p.addPoint(new PathPoint(Math.abs(this.x2 - (this.x + 1)), Math.abs(this.y2 - y), y, x + 1));
					paths.add(p);
				}
			} else {

				Path path = paths.get(0);

				Path p1 = new Path(paths.get(0).getVelocity());
				Path p2 = new Path(paths.get(0).getVelocity());
				Path p3 = new Path(paths.get(0).getVelocity());
				Path p4 = new Path(paths.get(0).getVelocity());

				p1.addAll(path.getPathPoints());
				p2.addAll(path.getPathPoints());
				p3.addAll(path.getPathPoints());
				p4.addAll(path.getPathPoints());

				int xx = path.getX();
				int yy = path.getY();

				// PathPoint ppu = new PathPoint(Math.abs(this.x2 - xx) + Math.abs((yy - 1) -
				// this.y2), yy - 1, xx);
				// PathPoint ppd = new PathPoint(Math.abs(this.x2 - xx) + Math.abs(this.y2 - (yy
				// + 1)), yy + 1, xx);
				// PathPoint ppl = new PathPoint(Math.abs((xx - 1) - this.x2) + Math.abs(this.y2
				// - yy), yy, xx - 1);
				// PathPoint ppr = new PathPoint(Math.abs(this.x2 - (xx + 1)) + Math.abs(this.y2
				// - yy), yy, xx + 1);

				PathPoint ppu = new PathPoint(x2 - xx, y2 - yy + 1, yy - 1, xx);
				PathPoint ppd = new PathPoint(x2 - xx, y2 - yy - 1, yy + 1, xx);
				PathPoint ppl = new PathPoint(x2 - xx + 1, y2 - yy, yy, xx - 1);
				PathPoint ppr = new PathPoint(x2 - xx - 1, y2 - yy, yy, xx + 1);

				if (this.map.getMap()[yy - 1][xx] != 1 && !path.getPathPoints().contains(ppu)) {
					p1.addPoint(ppu);
					paths.add(p1);
				}
				xx = xx - xx + xx;
				if (this.map.getMap()[yy + 1][xx] != 1 && !path.getPathPoints().contains(ppd)) {
					p2.addPoint(ppd);
					paths.add(p2);
				}
				xx = xx - xx + xx;
				if (xx != this.map.getMap()[0].length - 1 && this.map.getMap()[yy][xx + 1] != 1
						&& !path.getPathPoints().contains(ppr)) {
					p3.addPoint(ppr);
					paths.add(p3);
				}
				xx = xx - xx + xx;
				if (xx != 0 && this.map.getMap()[yy][xx - 1] != 1 && !path.getPathPoints().contains(ppl)) {
					p4.addPoint(ppl);
					paths.add(p4);
				}
				paths.remove(path);

			}

			Collections.sort(paths, (a, b) -> a.getValue() < b.getValue() ? -1 : a.getValue() == b.getValue() ? 0 : 1);
			// paths = (ArrayList<Path>) paths.stream().sorted((o1, o2) -> o1.getValue() -
			// o2.getValue())
			// .collect(Collectors.toList());

			for (int i = 1; i < paths.size(); i++) {
				if (paths.get(i).getValue() < paths.get(0).getValue()) {
					logger.info("Not the shortest");
				}
			}

			if (paths.isEmpty()) {
				break;
			}

			if (paths.get(0).getX() == this.x2 && paths.get(0).getY() == this.y2) {
				// logger.info("A HIT");
				break;
			}
		}
		if (paths.isEmpty()) {

			Path p = new Path(Velocity.UP);
			p.addPoint(new PathPoint(Math.abs(this.x2 - this.x), Math.abs(this.y2 - y), y, x));
			paths.add(p);
		}

		return paths;
	}

}
