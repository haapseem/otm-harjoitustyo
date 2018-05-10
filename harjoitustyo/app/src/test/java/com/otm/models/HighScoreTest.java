package com.otm.models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighScoreTest {

	public HighScoreTest() {
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

	// Create class test
	@Test
	public void createScore() {
		Highscore hs = new Highscore(1, 2);
		assertTrue(!hs.equals(null));
	}

	// get score and id
	@Test
	public void getScore() {
		Highscore hs = new Highscore(1, 2);
		assertTrue(hs.getScore() == 2 && hs.getId() == 1);
	}

	// get and set score
	@Test
	public void getSetScore() {
		Highscore hs = new Highscore(1, 2);
		hs.setScore(3);
		assertTrue(hs.getScore() == 3);
	}

	// get and set id
	@Test
	public void getSetId() {
		Highscore hs = new Highscore(1, 2);
		hs.setId(2);
		assertTrue(hs.getId() == 2);
	}

	// equals
	@Test
	public void equals() {
		Highscore hs = new Highscore(1, 2);
		Highscore hs1 = new Highscore(2, 2);
		Highscore hs2 = new Highscore(1, 2);
		assertTrue(hs.equals(hs2) && !hs.equals(hs1) && hs.equals(hs));
	}

	// right score
	@Test
	public void score() {
		Highscore hs = new Highscore(1, 2);
		assertTrue(hs.toString().equals("Highscore [score=2]"));
	}

}
