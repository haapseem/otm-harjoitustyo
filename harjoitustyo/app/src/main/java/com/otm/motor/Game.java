package com.otm.motor;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.otm.motor.path.PathFinder;
import com.otm.scenes.GameScene;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Game {

	protected static Logger logger = Logger.getLogger(Game.class.getName());

	private Pacman pacman;
	private Scene scene;
	private Map map;
	private ArrayList<Ghost> ghosts;

	public Game(final Stage stage) {
		pacman = new Pacman();
		ghosts = new ArrayList<Ghost>();
		map = new Map();

		(new PathFinder(13, 24, 16, 24, map)).getPath();

		ghosts.add(new Ghost());
		// scene = (new GameScene(map, pacman, ghosts)).getScene();
		/**
		 * Animation timer
		 */
		AnimationTimer timer = new AnimationTimer() {

			private long lastUpdate = 0;

			private int x = 0;
			private long fpsStart = 0;
			private long fps = 0;

			private GameScene gs = new GameScene(map, pacman, ghosts);

			@Override
			public void handle(long now) {

				// second = 1 ^ 9
				if (now - lastUpdate >= Math.pow(10, 9) / 60) {
					long start = new Date().getTime();
					scene = gs.update(pacman, ghosts);
					pacman.move(map);

					// Keypress event
					scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent event) {
							switch (event.getCode()) {
							case W:
							case UP:
								pacman.setVelocity(Velocity.UP);
								break;
							case S:
							case DOWN:
								pacman.setVelocity(Velocity.DOWN);
								break;
							case D:
							case RIGHT:
								pacman.setVelocity(Velocity.RIGHT);
								break;
							case A:
							case LEFT:
								pacman.setVelocity(Velocity.LEFT);
								break;
							default:
								break;
							}
						}
					});

					// draw
					stage.setScene(scene);
					if (x == 60) {
						fps = now - fpsStart;
						x = 0;
					}
					stage.setTitle((int) (Math.pow(10, 9) / (fps / 60)) + " fps");
					if (x == 0) {
						fpsStart = now;
					}
					x++;
					lastUpdate = now - (new Date().getTime() - start) * 1000000;
				}
			}
		};

		stage.show();
		timer.start();
	}

}
