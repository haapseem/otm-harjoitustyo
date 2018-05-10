package com.otm;

import com.otm.motor.Game;

import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class App extends Application {

	@Override
	public void start(Stage stage) {
		new Game(stage);
	}

	public static void main(String args[]) {
		launch(args);
	}
}
