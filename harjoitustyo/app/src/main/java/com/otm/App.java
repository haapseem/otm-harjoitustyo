package com.otm;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		Group root = new Group();
		Scene s = new Scene(root, 400, 400, Color.BLACK);

		Rectangle r = new Rectangle(25, 25, 250, 250);
		r.setFill(Color.BLUE);

		root.getChildren().add(r);
		stage.setScene(s);
		stage.show();
	}

	public static void Game(String args[]) {
		launch(args);
	}
}
