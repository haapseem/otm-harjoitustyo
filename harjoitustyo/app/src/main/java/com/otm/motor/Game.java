package com.otm.motor;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.otm.Db;
import com.otm.models.Highscore;
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

	private int score;
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
	Future<ArrayList<Path>> future1;
	Future<ArrayList<Path>> future2;
	Future<ArrayList<Path>> future3;
	private Db db;
	private Highscore hs;

	public Game(final Stage stage) {
		db = new Db();
		hs = db.getScore();
		score = 0;
		pacman = new Pacman();
		ghosts = new ArrayList<Ghost>();
		map = new Map();

		ghosts.add(new Ghost("red", map));
		ghosts.add(new Ghost("pink", map));
		ghosts.add(new Ghost("orange", map));
		ghosts.add(new Ghost("blue", map));

		/**
		 * Animation timer
		 */

		AnimationTimer timer = new AnimationTimer() {

			private long lastUpdate = 0;

			private int x = 0;
			private long fpsStart = 0;
			private long fps = 0;
			private int ghostMove = 0;

			private GameScene gs = new GameScene(map, pacman, ghosts);

			private boolean fRun = true;

			@Override
			public void handle(long now) {
				if (score == 0) {
					score = 0;
					map.newMap();
					gs = new GameScene(map, pacman, ghosts);
					hs = db.getScore();
				}

				if (fRun) {
					this.resetThreads0();
					this.resetThreads1();
					this.resetThreads2();
					this.resetThreads3();
					fRun = false;
				}

				if (future0.isDone()) {
					try {
						gs.addPaths(ghosts.get(0), future0.get());
						ghosts.get(0).turn(future0.get().get(0).getVelocity());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					this.resetThreads0();
				}

				if (future1.isDone()) {
					try {
						gs.addPaths(ghosts.get(1), future1.get());
						ghosts.get(1).turn(future1.get().get(0).getVelocity());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					this.resetThreads1();
				}

				if (future2.isDone()) {
					try {
						gs.addPaths(ghosts.get(2), future2.get());
						ghosts.get(2).turn(future2.get().get(0).getVelocity());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					this.resetThreads2();
				}

				if (future3.isDone()) {
					try {
						gs.addPaths(ghosts.get(3), future3.get());
						ghosts.get(3).turn(future3.get().get(0).getVelocity());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					this.resetThreads3();
				}

				// second = 1 ^ 9
				if (now - lastUpdate >= Math.pow(10, 9) / 60) {
					for (Ghost g : ghosts) {
						if ((int) g.getX() == (int) pacman.getX() && (int) g.getY() == (int) pacman.getY()) {
							startNew();
						}
					}
					int pacx = (int) pacman.getX();
					int pacy = (int) pacman.getY();

					// Eating and done check
					if (map.getMap()[pacy][pacx] == 3) {
						map.set(pacx, pacy);
						score++;
					}
					int done = 1;
					for (int[] i : map.getMap()) {
						for (int j : i) {
							if (j == 3) {
								done = 0;
							}
						}
					}
					if (done == 1) {
						reset();
						map.newMap();
						gs = new GameScene(map, pacman, ghosts);
					}

					long start = new Date().getTime();
					if (ghostMove == 9) {
						ghostMove = 0;
					} else {
						ghostMove++;
						ghosts.get(0).move();
						ghosts.get(1).move();
						ghosts.get(2).move();
						ghosts.get(3).move();
					}
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
					stage.setTitle(
							(int) (Math.pow(10, 9) / (fps / 60)) + " fps, Score: " + score + ", " + hs.toString());
					if (x == 0) {
						fpsStart = now;
					}
					x++;
					lastUpdate = now - (new Date().getTime() - start) * 1000000;
				}
			}

			private void resetThreads0() {
				thread0 = Executors.newCachedThreadPool();
				future0 = thread0.submit(new Callable<ArrayList<Path>>() {
					private PathFinder pf;

					@Override
					public ArrayList<Path> call() throws Exception {

						// red
						pf = new PathFinder((int) ghosts.get(0).getX(), (int) ghosts.get(0).getY(), (int) pacman.getX(),
								(int) pacman.getY(), map);
						pf.setVelocity(ghosts.get(0).getVelocity());
						return this.pf.getPath();
					}
				});
				thread0.shutdown();
			}

			private void resetThreads1() {
				thread1 = Executors.newCachedThreadPool();
				future1 = thread1.submit(new Callable<ArrayList<Path>>() {
					private PathFinder pf;

					@Override
					public ArrayList<Path> call() throws Exception {

						// pink
						int x = (int) pacman.getX();
						int y = (int) pacman.getY();
						if (pacman.getVelocity().equals(Velocity.DOWN)) {
							if (y + 4 > map.getMap().length - 3) {
								y = map.getMap().length - 3;
							} else if (map.getMap()[y + 4][x] == 1) {
								for (int i = 1; i < 4; i++) {
									if (map.getMap()[y + (4 - i)][x] != 1) {
										y = y + (4 - i);
										break;
									}
								}
							} else {
								y = y + 4;
							}
						} else if (pacman.getVelocity().equals(Velocity.UP)) {
							if (y - 4 < 2) {
								y = 2;
							} else if (map.getMap()[y - 4][x] == 1) {
								for (int i = 1; i < 4; i++) {
									if (map.getMap()[y - (4 - i)][x] != 1) {
										y = y - (4 - i);
										break;
									}
								}
							} else {
								y = y - 4;
							}
						} else if (pacman.getVelocity().equals(Velocity.RIGHT)) {
							if (x + 4 > map.getMap()[0].length - 3) {
								x = map.getMap()[0].length - 3;
							} else if (map.getMap()[y][x + 4] == 1) {
								for (int i = 1; i < 4; i++) {
									if (map.getMap()[y][x + (4 - i)] != 1) {
										x = x + (4 - i);
										break;
									}
								}
							} else {
								x = x + 4;
							}
						} else if (pacman.getVelocity().equals(Velocity.LEFT)) {
							if (x - 4 < 2) {
								x = 2;
							} else if (map.getMap()[y][x - 4] == 1) {
								for (int i = 1; i < 4; i++) {
									if (map.getMap()[y][x - (4 - i)] != 1) {
										x = x - (4 - i);
										break;
									}
								}
							} else {
								x = x - 4;
							}
						}

						pf = new PathFinder((int) ghosts.get(1).getX(), (int) ghosts.get(1).getY(), x, y, map);
						pf.setVelocity(ghosts.get(1).getVelocity());
						return this.pf.getPath();
					}
				});
				thread1.shutdown();
			}

			private void resetThreads2() {
				thread2 = Executors.newCachedThreadPool();
				future2 = thread2.submit(new Callable<ArrayList<Path>>() {
					private PathFinder pf;

					@Override
					public ArrayList<Path> call() throws Exception {
						// orange
						if (Math.abs(ghosts.get(2).getX() - pacman.getX())
								+ Math.abs(ghosts.get(2).getY() - pacman.getY()) < 8) {
							pf = new PathFinder((int) ghosts.get(2).getX(), (int) ghosts.get(2).getY(), 2,
									map.getMap().length - 3, map);
						} else {
							pf = new PathFinder((int) ghosts.get(2).getX(), (int) ghosts.get(2).getY(),
									(int) pacman.getX(), (int) pacman.getY(), map);
						}
						pf.setVelocity(ghosts.get(2).getVelocity());
						return this.pf.getPath();
					}
				});
				thread2.shutdown();
			}

			private void resetThreads3() {
				thread3 = Executors.newCachedThreadPool();
				future3 = thread3.submit(new Callable<ArrayList<Path>>() {
					private PathFinder pf;

					@Override
					public ArrayList<Path> call() throws Exception {
						// blue
						int px = (int) pacman.getX();
						int py = (int) pacman.getY();
						int rx = (int) ghosts.get(0).getX();
						int ry = (int) ghosts.get(0).getY();
						Velocity pv = pacman.getVelocity();
						int iix = px;
						int iiy = py;
						if (pv.equals(Velocity.UP)) {
							iix = px - 2;
							iiy = py - 2;
						} else if (pv.equals(Velocity.DOWN)) {
							iiy = py + 2;
						} else if (pv.equals(Velocity.RIGHT)) {
							iix = px + 2;
						} else {
							iix = px - 2;
						}

						// logger.info("ix : " + ix);
						int ix = rx + (iix - rx) * 2;
						// logger.info("iy : " + iy);
						// logger.info("ix : " + ix);
						int iy = ry + (iiy - ry) * 2;
						// logger.info("iy : " + iy);

						if (ix < 2)
							ix = 2;
						else if (ix > map.getMap()[0].length - 3)
							ix = map.getMap()[0].length - 3;

						if (iy < 2)
							iy = 2;
						else if (iy > map.getMap().length - 3)
							iy = map.getMap().length - 3;

						while (map.getMap()[iy][ix] == 1) {

							if (ix < 2)
								ix = 2;
							else if (ix > map.getMap()[0].length - 3)
								ix = map.getMap()[0].length - 3;

							if (iy < 2)
								iy = 2;
							else if (iy > map.getMap().length - 3)
								iy = map.getMap().length - 3;

							if (ix != rx && iy != ry) {
								if (map.getMap()[iy + 1][ix + 1] != 1) {
									ix++;
									iy++;
								} else if (map.getMap()[iy - 1][ix - 1] != 1) {
									ix--;
									iy--;
								} else {
									ix += (ix > rx ? 1 : ix < rx ? -1 : 0);
									iy += (iy > ry ? 1 : iy < ry ? -1 : 0);
								}
							} else if (ix != rx && iy == ry) {
								if (map.getMap()[iy][ix + 1] != 1) {
									ix++;
								} else if (map.getMap()[iy][ix - 1] != 1) {
									ix--;
								} else {
									ix += (ix > rx ? 1 : ix < rx ? -1 : 0);
								}
							} else if (ix == rx && iy != ry) {
								if (map.getMap()[iy + 1][ix] != 1) {
									iy++;
								} else if (map.getMap()[iy - 1][ix] != 1) {
									iy--;
								} else {
									iy += (iy > ry ? 1 : iy < ry ? -1 : 0);
								}
							}
						}
						pf = new PathFinder((int) ghosts.get(3).getX(), (int) ghosts.get(3).getY(), ix, iy, map);
						pf.setVelocity(ghosts.get(3).getVelocity());
						return this.pf.getPath();
					}
				});
				thread3.shutdown();
			}
		};

		stage.show();
		timer.start();
	}

	public void startNew() {
		if (score > hs.getScore()) {
			db.updateScore(score);
		}
		score = -1;
		reset();
	}

	public void reset() {
		pacman = new Pacman();
		ghosts = new ArrayList<Ghost>();
		map = new Map();

		ghosts.add(new Ghost("red", map));
		ghosts.add(new Ghost("pink", map));
		ghosts.add(new Ghost("orange", map));
		ghosts.add(new Ghost("blue", map));

	}
}
