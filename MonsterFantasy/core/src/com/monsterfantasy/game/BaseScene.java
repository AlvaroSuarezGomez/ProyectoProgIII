package com.monsterfantasy.game;

import com.badlogic.gdx.Screen;

public abstract class BaseScene implements Screen {

	protected Monsterfantasy game;
	
	public BaseScene (Monsterfantasy game) {
		this.game = game;
	}
	
	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {
		

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
