package com.otm.motor;

import java.util.logging.Logger;

public class Pacman extends Sprite {

	protected static Logger logger = Logger.getLogger(Pacman.class.getName());

	private Velocity v, waitingV;

	public Pacman() {
		super(15.5, 24);
		this.v = Velocity.RIGHT;
		this.waitingV = Velocity.NONE;
	}

	public void refresh(Map map) {
		if (this.waitingV == Velocity.NONE)
			return;

		if ((this.getX() % 2 == 0.0 || this.getX() % 2 == 1.0) && (this.getY() % 2 == 0.0 || this.getY() % 2 == 1.0)) {
			Velocity tempV = v;
			this.v = this.waitingV;
			if (!this.canMove(map)) {
				this.v = tempV;
				return;
			}
		}
	}

	public void setVelocity(Velocity v) {
		this.waitingV = v;
	}

	public Velocity getVelocity() {
		return this.v;
	}

	public boolean canMove(Map map) {
		if ((this.getX() % (int) this.getX() > 0.0 && this.getX() % (int) this.getX() < 1.0)
				|| (this.getY() % (int) this.getY() > 0.0 && this.getY() % (int) this.getY() < 1.0)) {
			return true;
		}
		switch (this.v) {
		case DOWN:
			return (map.getMap()[(int) this.getY() + 1][(int) this.getX()] != 1
					&& map.getMap()[(int) this.getY() + 1][(int) this.getX()] != 2);
		case UP:
			return (map.getMap()[(int) this.getY() - 1][(int) this.getX()] != 1
					&& map.getMap()[(int) this.getY() - 1][(int) this.getX()] != 2);
		case LEFT:
			return (map.getMap()[(int) this.getY()][(int) this.getX() - 1] != 1
					&& map.getMap()[(int) this.getY()][(int) this.getX() - 1] != 2);
		case RIGHT:
			return (map.getMap()[(int) this.getY()][(int) this.getX() + 1] != 1
					&& map.getMap()[(int) this.getY()][(int) this.getX() + 1] != 2);
		default:
			break;
		}
		return false;
	}

	public void move(Map map) {
		this.refresh(map);

		if (this.getX() == 1 && this.v == Velocity.LEFT) {
			this.setX(27.875);
		} else if (this.getX() == 28 && this.v == Velocity.RIGHT) {
			this.setX(1.125);
		}

		if (!this.canMove(map)) {
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
}
