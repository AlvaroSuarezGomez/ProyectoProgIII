package com.monsterfantasy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;

public class BattleScene extends BaseScene {
	private SpriteBatch batch;
	private Monsterfantasy game;
	private Texture background;
	private Heroe heroe;
	private Enemigo enemigo;
	private Partida partida;
	
	public BattleScene(Monsterfantasy game) {
		super(game);
		this.game = game;
		background = new Texture("Battle Background.png");
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		enemigo = new Enemigo(10, 10, 10, 10, false, 0, "WarGreymon", 0);
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		Gdx.app.log("Info","Combate comenzado");
		batch.end();
	}

	@Override
	public void dispose() {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		Gdx.app.log("MonsterFantasy", "Deteniendo aplicación");
		super.dispose();
	}

	public Heroe getHeroe() {
		return heroe;
	}

	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Monsterfantasy game) {
		this.game = game;
	}
}
