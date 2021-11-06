package com.monsterfantasy.game.battle;

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

public class Monsterfantasy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int x = 0;
	Camera cam = new OrthographicCamera();
	Vector3 cam_pos = new Vector3(x, 1000, 1000);
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	}
	

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		cam.project(cam_pos);

		batch.draw(img, x, 0, 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		Gdx.app.log("MonsterFantasy", "Deteniendo aplicación");
		batch.dispose();
		img.dispose();
	}
}
