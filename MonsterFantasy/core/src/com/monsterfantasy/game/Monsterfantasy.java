package com.monsterfantasy.game;


import com.badlogic.gdx.ApplicationAdapter; 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;

public class Monsterfantasy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Avatar player = new Avatar();
	
	@Override
	public void create () {
		img = new Texture("amogus.png");
		batch = new SpriteBatch();
		batch.begin();
		batch.end();
	}
	

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		Controller.Control(player);
		System.out.println(player.getX());
		batch.draw(img, player.getX(), player.getY(), 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		Gdx.app.log("MonsterFantasy", "Deteniendo aplicación");
		batch.dispose();
		img.dispose();
	}
}
