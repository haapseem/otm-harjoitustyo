package com.otm;

import com.otm.motor.Game;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		new Game(stage);
	}

	public static void Game(String args[]) {
		launch(args);
	}
}
