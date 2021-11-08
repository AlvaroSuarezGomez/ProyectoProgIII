package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.physics.box2d.Box2D;

public class Avatar {
	private float x;
	private float y;
	int speed;
	Box2D collider;
	
	public Avatar() {
		float x = Overworld.getSpawnpointX();
		float y = Overworld.getSpawnpointY();
	}

	public float getX() {
		return x;
	}

	public void setX(float f) {
		this.x = f;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
