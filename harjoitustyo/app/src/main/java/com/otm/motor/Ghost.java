package com.otm.motor;

import java.util.Random;
import java.util.logging.Logger;

public class Ghost extends Sprite {
	protected static Logger logger = Logger.getLogger(Ghost.class.getName());

	private String name;
	private Velocity v;
	private Map map;
	private int moveToken;

	public Ghost(String name, Map map) {
		super(14, 15);
		this.name = name;
		v = Velocity.UP;
		this.map = map;
		this.moveToken = 0;
	}

	public boolean canMove() {
		if ((this.getX() % (int) this.getX() > 0.0 && this.getX() % (int) this.getX() < 1.0)
				|| (this.getY() % (int) this.getY() > 0.0 && this.getY() % (int) this.getY() < 1.0)) {
			return true;
		}
		switch (this.v) {
		case DOWN:
			return (map.getMap()[(int) this.getY() + 1][(int) this.getX()] != 1);
		case UP:
			return (map.getMap()[(int) this.getY() - 1][(int) this.getX()] != 1);
		case LEFT:
			return (map.getMap()[(int) this.getY()][(int) this.getX() - 1] != 1);
		case RIGHT:
			return (map.getMap()[(int) this.getY()][(int) this.getX() + 1] != 1);
		default:
			break;
		}
		return false;
	}

	public void move() {
		if (moveToken == 2) {
			moveToken = 1;
		} else if (moveToken == 1) {
			moveToken = 0;
			return;
		}

		if (this.getX() == 1 && this.v == Velocity.LEFT) {
			this.v = Velocity.RIGHT;
		} else if (this.getX() == 28 && this.v == Velocity.RIGHT) {
			this.v = Velocity.LEFT;
		}

		if (!this.canMove()) {
			this.randomTurn();
			return;
		}

		switch (this.v) {
		case DOWN:
			this.setY(this.getY() + 0.125);
			break;
		case UP:
			this.setY(this.getY() - 0.125);
			break;
		case LEFT:
			this.setX(this.getX() - 0.125);
			break;
		case RIGHT:
			this.setX(this.getX() + 0.125);
			break;
		default:
			break;
		}
	}

	public boolean turn(Velocity t) {
		if (t.equals(this.v)) {
			return false;
		} else if (!((this.getX() % 2 == 0 || this.getX() % 2 == 1)
				&& (this.getY() % 2 == 0 || this.getY() % 2 == 1))) {
			return false;
		} else if ((t.equals(Velocity.DOWN) && this.v.equals(Velocity.UP))
				|| (t.equals(Velocity.UP) && this.v.equals(Velocity.DOWN))
				|| (t.equals(Velocity.LEFT) && this.v.equals(Velocity.RIGHT))
				|| (t.equals(Velocity.RIGHT) && this.v.equals(Velocity.LEFT))) {
			return false;
		} else if ((t.equals(Velocity.DOWN) && (map.getMap()[(int) this.getY() + 1][(int) this.getX()] == 1))
				|| (t.equals(Velocity.UP) && (map.getMap()[(int) this.getY() - 1][(int) this.getX()] == 1))
				|| (t.equals(Velocity.LEFT) && (map.getMap()[(int) this.getY()][(int) this.getX() - 1] == 1))
				|| (t.equals(Velocity.RIGHT) && (map.getMap()[(int) this.getY()][(int) this.getX() + 1] == 1))) {
			return false;
		} else {
			logger.info("THIS V: " + v + ", THIS T: " + t);
			this.v = t;
			moveToken = 2;
			this.move();

			return true;
		}
	}

	private void randomTurn() {
		int i = new Random().nextInt(4);
		Velocity a;
		if (i == 1) {
			a = Velocity.DOWN;
		} else if (i == 2) {
			a = Velocity.UP;
		} else if (i == 3) {
			a = Velocity.RIGHT;
		} else {
			a = Velocity.LEFT;
		}
		boolean x = turn(a);
		if (!x) {
			this.randomTurn();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ghost other = (Ghost) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
