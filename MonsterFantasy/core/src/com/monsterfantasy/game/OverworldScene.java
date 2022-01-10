package com.monsterfantasy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Celda;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;
import com.monsterfantasy.game.overworld.TipoCelda;

public class OverworldScene extends ScreenAdapter {
	private SpriteBatch batch;
	private Monsterfantasy game;
	private TextureRegion region;
	private Texture gameMenu;
	private Heroe heroe;
	private Overworld map = new Overworld();
	private Avatar player = new Avatar(getMap().getSpawnpointX(), getMap().getSpawnpointY(), getHeroe());
	private Partida partida;
	private boolean isMenuOpened = false;
	private BitmapFont menuFont;
	
	public OverworldScene(Monsterfantasy game) {
		super();
		this.game = game;
		game.setCam(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		map.setGame(game);
		map.setTileSet(new Texture("Overworld tileset.png"));
		map.setSuelo(new TextureRegion(getMap().getTileSet(), 0, 0, 64, 64));
		map.setArbol(new TextureRegion(getMap().getTileSet(), 704, 0, 64, 64));
		gameMenu =  new Texture(Gdx.files.internal("GameMenu.png"));
		Controller.SetTexture(getPlayer());
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		menuFont = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		game.getCam().position.set(getPlayer().getX(), getPlayer().getY(), 0);
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		Controller.player = getPlayer();
		game.getCam().update();
		getMap().setTileSet(new Texture("Overworld tileset.png"));
		getMap().setSuelo(new TextureRegion(getMap().getTileSet(), 16, 0, 16, 16));
		map.draw(batch, 0);
		batch.draw(getPlayer().getP_texture_region(), getPlayer().getX(), getPlayer().getY());
		
		
		if (isMenuOpened) {
			batch.draw(gameMenu, getPlayer().getX() + 80, getPlayer().getY() - 100, 320, 400);
			menuFont.draw(batch, "Dinero: " + heroe.getDinero() + "G", getPlayer().getX() + 100, getPlayer().getY() - 60);
			
		} else Controller.Control();
		
		if ((Gdx.input.isKeyJustPressed(Keys.C)) && (!isMenuOpened)) {
			isMenuOpened = true;
		}
		
		if (isMenuOpened) {
			if (Gdx.input.isKeyJustPressed(Keys.X)) {
				isMenuOpened = false;
			}
		}
		
		
		
		batch.end();
	}
	
	public enum menuSelection {
		Cerrar,
		Equipamiento,
		Salir
	}

	@Override
	public void dispose() {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		map.dispose();
		gameMenu.dispose();
		super.dispose();
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
}
