package com.otm.scenes;

import java.util.ArrayList;
import java.util.HashMap;
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
	private int boxS = 20;
	private PathFinder pf;
	private int time = 1;
	// private HashMap<Ghost, Path> paths;
	private ArrayList<PathPoint> points;
	private HashMap<Ghost, ArrayList<Path>> paths;

	public GameScene(Map map, Pacman pac, ArrayList<Ghost> ghosts) {

		mapp = map.getMap();
		this.paths = new HashMap<Ghost, ArrayList<Path>>();
		this.points = new ArrayList<PathPoint>();

		this.update(pac, ghosts);
	}

	public void addPaths(Ghost g, ArrayList<Path> paths) {
		this.paths.put(g, paths);
	}

	public Scene update(Pacman pac, ArrayList<Ghost> ghosts) {

		Group root = new Group();

		this.s = new Scene(root, boxS * this.mapp[0].length, boxS * this.mapp.length, Color.BLACK);

		if ((this.paths.containsKey(ghosts.get(0)) && this.paths.containsKey(ghosts.get(1))
				&& this.paths.containsKey(ghosts.get(2)))) {
			// red
			this.points = this.paths.get(ghosts.get(0)).get(0).getPathPoints();

			for (PathPoint p : points) {
				Rectangle rec = new Rectangle(boxS * p.getX() + (boxS * 0.4), boxS * p.getY() + (boxS * 0.4),
						boxS * 0.2, boxS * 0.2);
				rec.setFill(Color.RED);
				root.getChildren().add(rec);
			}
			// pink
			this.points = this.paths.get(ghosts.get(1)).get(0).getPathPoints();

			for (PathPoint p : points) {
				Rectangle rec = new Rectangle(boxS * p.getX() + (boxS * 0.4), boxS * p.getY() + (boxS * 0.4),
						boxS * 0.2, boxS * 0.2);
				rec.setFill(Color.PINK);
				root.getChildren().add(rec);
			}
			// orange
			this.points = this.paths.get(ghosts.get(2)).get(0).getPathPoints();

			for (PathPoint p : points) {
				Rectangle rec = new Rectangle(boxS * p.getX() + (boxS * 0.4), boxS * p.getY() + (boxS * 0.4),
						boxS * 0.2, boxS * 0.2);
				rec.setFill(Color.ORANGE);
				root.getChildren().add(rec);
			}
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

		Circle c3 = new Circle(boxS * (ghosts.get(1).getX() + 1) - (boxS / 2),
				boxS * (ghosts.get(1).getY() + 1) - (boxS / 2), boxS / 2);
		c3.setFill(Color.PINK);
		root.getChildren().add(c3);

		Circle c4 = new Circle(boxS * (ghosts.get(2).getX() + 1) - (boxS / 2),
				boxS * (ghosts.get(2).getY() + 1) - (boxS / 2), boxS / 2);
		c4.setFill(Color.ORANGE);
		root.getChildren().add(c4);

		return this.s;

	}

	public Scene getScene() {
		return this.s;
	}

}
