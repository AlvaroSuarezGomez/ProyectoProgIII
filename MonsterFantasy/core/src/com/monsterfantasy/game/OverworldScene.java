package com.monsterfantasy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;

public class OverworldScene extends BaseScene {
	private SpriteBatch batch;
	private Monsterfantasy game;
	private Camera cam;
	private TextureRegion region;
	private Heroe heroe;
	private Overworld map = new Overworld();
	private Avatar player = new Avatar(getMap().getSpawnpointX(), getMap().getSpawnpointY(), getHeroe());
	private Partida partida;
	
	public OverworldScene(Monsterfantasy game) {
		super(game);
		this.game = game;
		setCam(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		map.setGame(game);
		map.setTileSet(new Texture("Overworld tileset.png"));
		map.setSuelo(new TextureRegion(getMap().getTileSet(), 0, 0, 64, 64));
		Controller.SetTexture(getPlayer());
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(getCam().combined);
		batch.begin();
		Controller.player = getPlayer();
		getCam().position.set(getPlayer().getX(), getPlayer().getY(), 0);
		getCam().update();
		Controller.Control();
		getMap().setTileSet(new Texture("Overworld tileset.png"));
		getMap().setSuelo(new TextureRegion(getMap().getTileSet(), 16, 0, 16, 16));
		map.draw(batch, 0);
		batch.draw(getPlayer().getP_texture_region(), getPlayer().getX(), getPlayer().getY());
		batch.end();
	}

	@Override
	public void dispose() {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		//Gdx.app.log("MonsterFantasy", "Deteniendo aplicación");
		map.dispose();
		//batch.dispose();
		//super.dispose();
	}

	public Heroe getHeroe() {
		return heroe;
	}

	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}

	public Avatar getPlayer() {
		return player;
	}

	public void setPlayer(Avatar player) {
		this.player = player;
	}

	public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

	public Overworld getMap() {
		return map;
	}

	public void setMap(Overworld map) {
		this.map = map;
	}

	public Monsterfantasy getGame() {
		return game;
	}

	public void setGame(Monsterfantasy game) {
		this.game = game;
	}

	public Camera getCam() {
		return cam;
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}
}
