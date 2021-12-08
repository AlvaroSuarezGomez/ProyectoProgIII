package com.monsterfantasy.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
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

public class Monsterfantasy extends Game {
	private SpriteBatch batch;
	private Heroe heroe;
	private Camera cam;
	private Partida partida;
	private OverworldScene overworld;
	private BattleScene battleScene;
	private Screen currentScreen;
	
	public Monsterfantasy() {
		
	}
	
	@Override
	public void create () {
		setCam(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		batch = new SpriteBatch();
		
		overworld = new OverworldScene(this);
		battleScene = new BattleScene(this);
		this.screen = overworld;
	}
	
	
	@Override
	public void render () {		
		super.render();
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			this.screen.dispose();
			this.setScreen(new BattleScene(this));
		}
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			this.screen.dispose();
			this.setScreen(new OverworldScene(this));
		}
	}
	
	@Override
	public void dispose () {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		Gdx.app.log("MonsterFantasy", "Deteniendo aplicaci�n");
		super.dispose();
		this.batch = batch;
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


	SpriteBatch getBatch() {
		return batch;
	}


	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}


	public OverworldScene getOverworld() {
		return overworld;
	}


	public void setOverworld(OverworldScene overworld) {
		this.overworld = overworld;
	}


	public BattleScene getBattleScene() {
		return battleScene;
	}


	public void setBattleScene(BattleScene battleScene) {
		this.battleScene = battleScene;
	}


	public Screen getCurrentScreen() {
		return currentScreen;
	}


	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}
	
	public Camera getCam() {
		return cam;
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}

}
