package com.monsterfantasy.game;


import com.badlogic.gdx.ApplicationAdapter; 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Personaje;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Celda;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;

public class Monsterfantasy extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion region;
	private Heroe heroe;
	Overworld map = new Overworld();
	private Avatar player = new Avatar(map.getSpawnpointX(), map.getSpawnpointY(), getHeroe());
	private Partida partida;
	Camera cam;
	
	
	@Override
	public void create () {
		int vpWidth = Gdx.graphics.getWidth(), vpHeight = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(vpWidth, vpHeight);
		batch = new SpriteBatch();
		batch.begin();
		map.setTileSet(new Texture("Overworld tileset.png"));
		map.setSuelo(new TextureRegion(map.getTileSet(), 0, 0, 64, 64));
		Controller.SetTexture(getPlayer());
		batch.end();
		
	}
	

	@Override
	public void render () {		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		Controller.player = getPlayer();
		cam.position.set(getPlayer().getX(), getPlayer().getY(), 0);
		cam.update();
		Controller.Control();
		map.setTileSet(new Texture("Overworld tileset.png"));
		map.setSuelo(new TextureRegion(map.getTileSet(), 16, 0, 16, 16));
		map.draw(batch, 0);
		batch.draw(getPlayer().getP_texture_region(), getPlayer().getX(), getPlayer().getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		Gdx.app.log("MonsterFantasy", "Deteniendo aplicación");
		map.dispose();
		batch.dispose();
	}


	public Avatar getPlayer() {
		return player;
	}


	public void setPlayer(Avatar player) {
		this.player = player;
	}


	public Heroe getHeroe() {
		return heroe;
	}


	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}


	public Partida getPartida() {
		return partida;
	}


	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}
