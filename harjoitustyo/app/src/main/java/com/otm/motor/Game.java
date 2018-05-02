package com.otm.motor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.otm.motor.path.Path;
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
	private ExecutorService thread0;
	private ExecutorService thread1;
	private ExecutorService thread2;
	private ExecutorService thread3;
	// private HashMap<Ghost, Future<ArrayList<Path>>> paths;
	Future<ArrayList<Path>> future0;

	public Game(final Stage stage) {
		pacman = new Pacman();
		ghosts = new ArrayList<Ghost>();
		map = new Map();

		// paths = new HashMap<Ghost, Future<ArrayList<Path>>>();

		(new PathFinder(13, 24, 16, 24, map)).getPath();

		ghosts.add(new Ghost("red", map));

		// resetThreads();

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

			private boolean fRun = true;

			@Override
			public void handle(long now) {

				if (fRun) {
					//
					// thread0 = Executors.newCachedThreadPool();
					//
					// future0 = thread0.submit(new Callable<ArrayList<Path>>() {
					//
					// private PathFinder pf;
					//
					// @Override
					// public ArrayList<Path> call() throws Exception {
					//
					// pf = new PathFinder((int) ghosts.get(0).getX(), (int) ghosts.get(0).getY(),
					// (int) pacman.getX(), (int) pacman.getY(), map);
					//
					// return this.pf.getPath();
					// }
					//
					// });
					// thread0.shutdown();
					this.resetThreads();
					fRun = false;
				}

				if (allDone(future0)) {
					HashMap<Ghost, ArrayList<Path>> gh = new HashMap<Ghost, ArrayList<Path>>();
					try {
						gh.put(ghosts.get(0), future0.get());
						ghosts.get(0).turn(future0.get().get(0).getVelocity());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					gs.addPaths(gh);
					this.resetThreads();
				}

				// second = 1 ^ 9
				if (now - lastUpdate >= Math.pow(10, 9) / 60) {
					long start = new Date().getTime();
					ghosts.get(0).move();
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

			private boolean allDone(Future f) {
				return f.isDone();
			}

			private boolean allDone(Collection<Future<ArrayList<Path>>> collection) {
				// return false;
				for (Future<ArrayList<Path>> f : collection) {
					if (!f.isDone()) {
						return false;
					}
				}
				return true;
			}

			private void resetThreads() {
				thread0 = Executors.newCachedThreadPool();

				future0 = thread0.submit(new Callable<ArrayList<Path>>() {

					private PathFinder pf;

					@Override
					public ArrayList<Path> call() throws Exception {

						pf = new PathFinder((int) ghosts.get(0).getX(), (int) ghosts.get(0).getY(), (int) pacman.getX(),
								(int) pacman.getY(), map);

						return this.pf.getPath();
					}

				});
				thread0.shutdown();
			}
		};

		stage.show();
		timer.start();
	}
}
