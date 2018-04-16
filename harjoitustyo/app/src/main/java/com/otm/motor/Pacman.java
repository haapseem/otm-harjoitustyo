package com.otm.motor;

import java.util.logging.Logger;

public class Pacman {

	protected static Logger logger = Logger.getLogger(Pacman.class.getName());

	private int x, y;

	public Pacman(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
