package com.otm;

import com.otm.scenes.GameScene;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		// Group root = new Group();
		// Scene s = new Scene(root, 400, 400, Color.BLACK);
		//
		// Rectangle r = new Rectangle(25, 25, 250, 250);
		// r.setFill(Color.BLUE);
		//
		// root.getChildren().add(r);
		stage.setScene((new GameScene()).getScene());
		stage.show();
	}

	public static void Game(String args[]) {
		launch(args);
	}
}
