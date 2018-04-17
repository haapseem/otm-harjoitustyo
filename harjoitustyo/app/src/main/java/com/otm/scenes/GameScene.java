package com.otm.scenes;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.otm.motor.Ghost;
import com.otm.motor.Map;
import com.otm.motor.Pacman;

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

	public GameScene(Map map, Pacman pac, ArrayList<Ghost> ghosts) {

		mapp = map.getMap();

		Group root = new Group();
		this.s = new Scene(root, boxS * this.mapp[0].length, boxS * this.mapp.length, Color.BLACK);

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
	}

	public Scene update(Pacman pac, ArrayList<Ghost> ghosts) {

		Group root = new Group();

		this.s = new Scene(root, boxS * this.mapp[0].length, boxS * this.mapp.length, Color.BLACK);

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

		return this.s;

	}

	public Scene getScene() {
		return this.s;
	}

}
