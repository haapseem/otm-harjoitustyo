package com.otm.motor;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.otm.scenes.GameScene;

import javafx.stage.Stage;

public class Game {

	protected static Logger logger = Logger.getLogger(Game.class.getName());

	public Game(Stage stage) {
		Map map = new Map();
		Pacman pacman = new Pacman(0, 0);
		ArrayList<Ghost> ghosts = new ArrayList();
		logger.info("Game is loading");
		stage.setScene((new GameScene(map, pacman, ghosts)).getScene());
		stage.show();
	}
}
