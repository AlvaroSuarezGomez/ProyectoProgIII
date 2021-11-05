package com.monsterfantasy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class monsterfantasy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Camera cam = new OrthographicCamera();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("amogus.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0, 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
