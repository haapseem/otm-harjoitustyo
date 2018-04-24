package com.otm.scenes;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.otm.motor.Ghost;
import com.otm.motor.Map;
import com.otm.motor.Pacman;
import com.otm.motor.path.Path;
import com.otm.motor.path.PathFinder;
import com.otm.motor.path.PathPoint;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("restriction")
public class GameScene {

	protected static Logger logger = Logger.getLogger(GameScene.class.getName());

	private Scene s;
	private int[][] mapp;
	private int boxS = 30;
	private PathFinder pf;

	public GameScene(Map map, Pacman pac, ArrayList<Ghost> ghosts) {

		mapp = map.getMap();
		this.pf = new PathFinder(13, 24, 16, 24, map);

		/*
		 * Group root = new Group();
		 * 
		 * 
		 * this.s = new Scene(root, boxS * this.mapp[0].length, boxS * this.mapp.length,
		 * Color.BLACK);
		 * 
		 * for (int y = 0; y < this.mapp.length; y++) { for (int x = 0; x <
		 * this.mapp[y].length; x++) { if (this.mapp[y][x] == 1) { // if (y <
		 * this.map.length - 1) { if (y < this.mapp.length - 1 && this.mapp[y + 1][x] ==
		 * 1) { Rectangle r = new Rectangle(boxS * x + (boxS * 0.4), boxS * y + (boxS *
		 * 0.5), boxS * 0.2, boxS * 0.5); r.setFill(Color.BLUE);
		 * root.getChildren().add(r); } if (y > 0 && this.mapp[y - 1][x] == 1) {
		 * Rectangle r = new Rectangle(boxS * x + (boxS * 0.4), boxS * y, boxS * 0.2,
		 * boxS * 0.5); r.setFill(Color.BLUE); root.getChildren().add(r); } if (x <
		 * this.mapp[y].length - 1 && (this.mapp[y][x + 1] == 1 || this.mapp[y][x + 1]
		 * == 2)) { Rectangle r = new Rectangle(boxS * x + (boxS * 0.5), boxS * y +
		 * (boxS * 0.4), boxS * 0.5, boxS * 0.2); r.setFill(Color.BLUE);
		 * root.getChildren().add(r); } if (x > 0 && (this.mapp[y][x - 1] == 1 ||
		 * this.mapp[y][x - 1] == 2)) { Rectangle r = new Rectangle(boxS * x, boxS * y +
		 * (boxS * 0.4), boxS * 0.5, boxS * 0.2); r.setFill(Color.BLUE);
		 * root.getChildren().add(r); } } else if (this.mapp[y][x] == 2) { Rectangle r =
		 * new Rectangle(boxS * x, boxS * y + (boxS * 0.4), boxS, boxS * 0.2);
		 * r.setFill(Color.WHITE); root.getChildren().add(r); } } } Circle c = new
		 * Circle(boxS * (pac.getX() + 1) - (boxS / 2), boxS * (pac.getY() + 1) - (boxS
		 * / 2), boxS / 2); c.setFill(Color.YELLOW); root.getChildren().add(c);
		 * 
		 * logger.info(ghosts.get(0).getX() + ""); logger.info(ghosts.get(0).getY() +
		 * ""); Circle c2 = new Circle(boxS * (ghosts.get(0).getX() + 1) - (boxS / 2),
		 * boxS * (ghosts.get(0).getY() + 1) - (boxS / 2), boxS / 2);
		 * c2.setFill(Color.RED); root.getChildren().add(c2);
		 */

		this.update(pac, ghosts);
	}

	public Scene update(Pacman pac, ArrayList<Ghost> ghosts) {

		Group root = new Group();

		this.s = new Scene(root, boxS * this.mapp[0].length, boxS * this.mapp.length, Color.BLACK);

		// this.pf.changeXsAndYs((int) ghosts.get(0).getX(), (int) pac.getX(), (int)
		// ghosts.get(0).getY(),
		// (int) pac.getY());

		// logger.info("" + (int) ghosts.get(0).getX());
		// logger.info("" + (int) ghosts.get(0).getY());
		// logger.info("" + (int) pac.getX());
		// logger.info("" + (int) pac.getY());

		// ArrayList<PathPoint> points = (new PathFinder((int) ghosts.get(0).getX(),
		// (int) pac.getX(),
		// (int) ghosts.get(0).getY(), (int) pac.getY(), (new Map()))).getPath();

		try {
			if (!((int) pac.getX() == 16 && (int) pac.getY() == 24)) {

				for (Path path : new PathFinder((int) ghosts.get(0).getX(), (int) ghosts.get(0).getY(),
						(int) pac.getX(), (int) pac.getY(), (new Map())).getPath()) {
					for (PathPoint p : path.getPathPoints()) {
						Rectangle rec = new Rectangle(boxS * p.getX() + (boxS * 0.4), boxS * p.getY() + (boxS * 0.4),
								boxS * 0.2, boxS * 0.2);
						rec.setFill(Color.RED);
						root.getChildren().add(rec);
					}
				}

				// ArrayList<PathPoint> points = new PathFinder((int) ghosts.get(0).getX(),
				// (int) ghosts.get(0).getY(),
				// (int) pac.getX(), (int) pac.getY(), (new
				// Map())).getPath().get(0).getPathPoints();
				this.pf.changeXsAndYs((int) ghosts.get(0).getX(), (int) pac.getX(), (int) ghosts.get(0).getY(),
						(int) pac.getY());

				ArrayList<PathPoint> points = this.pf.getPath().get(0).getPathPoints();

				try {
					for (PathPoint p : points) {
						Rectangle rec = new Rectangle(boxS * p.getX() + (boxS * 0.4), boxS * p.getY() + (boxS * 0.4),
								boxS * 0.2, boxS * 0.2);
						rec.setFill(Color.GREEN);
						root.getChildren().add(rec);
					}
				} catch (Exception e) {

					logger.info("Error: " + e);
				}
			}
		} catch (Exception e) {
			logger.info("Error: " + e);
			e.printStackTrace();
		}

		for (int y = 0; y < this.mapp.length; y++) {
			for (int x = 0; x < this.mapp[y].length; x++) {
				if (this.mapp[y][x] == 1) {
					// if (y < this.map.length - 1) {
					if (y < this.mapp.length - 1 && this.mapp[y + 1][x] == 1) {
						Rectangle r = new Rectangle(boxS * x + (boxS * 0.4), boxS * y + (boxS * 0.5), boxS * 0.2,
								boxS * 0.5);
						r.setFill(Color.BLUE);
						root.getChildren().add(r);
					}
					if (y > 0 && this.mapp[y - 1][x] == 1) {
						Rectangle r = new Rectangle(boxS * x + (boxS * 0.4), boxS * y, boxS * 0.2, boxS * 0.5);
						r.setFill(Color.BLUE);
						root.getChildren().add(r);
					}
					if (x < this.mapp[y].length - 1 && (this.mapp[y][x + 1] == 1 || this.mapp[y][x + 1] == 2)) {
						Rectangle r = new Rectangle(boxS * x + (boxS * 0.5), boxS * y + (boxS * 0.4), boxS * 0.5,
								boxS * 0.2);
						r.setFill(Color.BLUE);
						root.getChildren().add(r);
					}
					if (x > 0 && (this.mapp[y][x - 1] == 1 || this.mapp[y][x - 1] == 2)) {
						Rectangle r = new Rectangle(boxS * x, boxS * y + (boxS * 0.4), boxS * 0.5, boxS * 0.2);
						r.setFill(Color.BLUE);
						root.getChildren().add(r);
					}
				} else if (this.mapp[y][x] == 2) {
					Rectangle r = new Rectangle(boxS * x, boxS * y + (boxS * 0.4), boxS, boxS * 0.2);
					r.setFill(Color.WHITE);
					root.getChildren().add(r);
				}
			}
		}

		Circle c = new Circle(boxS * (pac.getX() + 1) - (boxS / 2), boxS * (pac.getY() + 1) - (boxS / 2), boxS / 2);
		c.setFill(Color.YELLOW);
		root.getChildren().add(c);

		Circle c2 = new Circle(boxS * (ghosts.get(0).getX() + 1) - (boxS / 2),
				boxS * (ghosts.get(0).getY() + 1) - (boxS / 2), boxS / 2);
		c2.setFill(Color.RED);
		root.getChildren().add(c2);

		return this.s;

	}

	public Scene getScene() {
		return this.s;
	}

}
