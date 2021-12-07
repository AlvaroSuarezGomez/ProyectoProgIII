package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Personaje;

public class Avatar {
	private float x;
	private float y;
	private int speed;
	private Texture p_texture;
	private TextureRegion p_texture_region;
	private Heroe heroe;
	
	public Avatar(float x, float y, Heroe heroe) {
		this.x = x;
		this.y = y;
		this.heroe = heroe;
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Heroe getHeroe() {
		return heroe;
	}

	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}
}
