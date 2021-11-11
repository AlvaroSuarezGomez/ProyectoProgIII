package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2D;

public class Avatar {
	private float x;
	private float y;
	int speed;
	private Texture p_texture;
	private TextureRegion p_texture_region;
	
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

	public Texture getP_texture() {
		return p_texture;
	}

	public void setP_texture(Texture p_texture) {
		this.p_texture = p_texture;
	}

	public TextureRegion getP_texture_region() {
		return p_texture_region;
	}

	public void setP_texture_region(TextureRegion p_texture_region) {
		this.p_texture_region = p_texture_region;
	}
}
