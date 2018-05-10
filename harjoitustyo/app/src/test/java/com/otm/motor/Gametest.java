package com.otm.motor;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Gametest {

	private Stage stage = new Stage();
	private Game game = new Game(stage);

	public Gametest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Tests
	 */

	@Test
	public void createGame() {
		Stage s = new Stage();
		Game g = new Game(stage);
		assertTrue(!g.equals(null));
	}

}
