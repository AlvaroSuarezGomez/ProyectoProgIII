package com.monsterfantasy.game;

import java.io.Serializable;

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
import com.monsterfantasy.game.overworld.GestionMapa;
import com.monsterfantasy.game.overworld.Overworld;
import com.monsterfantasy.game.overworld.TipoCelda;

public class OverworldScene extends ScreenAdapter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4139408601941798510L;
	private SpriteBatch batch;
	private Monsterfantasy game;
	private TextureRegion region;
	private Texture gameMenu;
	private Heroe heroe;
	private Overworld map = new Overworld();
	private Avatar player = new Avatar(0, 0, heroe);
	private Partida partida;
	private boolean isMenuOpened = false;
	private BitmapFont menuFont;
	private boolean debugMode = false;
	private boolean startBattle = false;
	
	public OverworldScene(Monsterfantasy game) {
		super();
		this.game = game;
		game.setCam(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		map.setCeldas(GestionMapa.cargafichero("mapa"));
		map.setTileSet(new Texture("Overworld tileset.png"));
		map.setSuelo(new TextureRegion(getMap().getTileSet(), 16, 0, 16, 16));
		map.setArbol(new TextureRegion(getMap().getTileSet(), 160, 0, 16, 16));
		map.setHierba(new TextureRegion(getMap().getTileSet(), 80, 16, 16, 16));
		map.setShop(new Texture("shop_sprite.png"));
		map.setTienda(new TextureRegion(map.getShop(), 0, 0, 16, 16));
		gameMenu =  new Texture(Gdx.files.internal("GameMenu.png"));
		Controller.overworld = map;
		Controller.setTexture(getPlayer());
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		menuFont = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		Controller.setOverworldScene(this);
		player.setX(game.getX());
		player.setY(game.getY());
		player.setP_texture_region(new TextureRegion(player.getP_texture(), 0, 0, 64, 64));
		
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		game.getCam().position.set(getPlayer().getX(), getPlayer().getY(), 0);
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		Controller.player = getPlayer();
		game.getCam().update();
		map.draw(batch, 0);
		batch.draw(player.getP_texture_region(), player.getX(), player.getY(), 64, 64);
		
		if (debugMode) {
			if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			map.getCeldas()[(int) (player.getX()/64)][(int) (player.getY()/64)].setTipo(TipoCelda.Arbol);
			}	else if (Gdx.input.isKeyJustPressed(Keys.P)) {
				map.getCeldas()[(int) (player.getX()/64)][(int) (player.getY()/64)].setTipo(TipoCelda.Hierba);
			}	else if (Gdx.input.isKeyJustPressed(Keys.L)) {
				map.getCeldas()[(int) (player.getX()/64)][(int) (player.getY()/64)].setTipo(TipoCelda.Tienda);
			}
		}
		
		
		if (isMenuOpened) {
			batch.draw(gameMenu, getPlayer().getX() + 80, getPlayer().getY() - 100, 320, 400);
			menuFont.draw(batch, "Dinero: " + heroe.getDinero() + "G", getPlayer().getX() + 100, getPlayer().getY() - 60);
			menuFont.draw(batch, "Nivel: " + heroe.getNv(), getPlayer().getX() + 100, getPlayer().getY() - 30);
			
			if (Gdx.input.isKeyJustPressed(Keys.X)) {
				isMenuOpened = false;
			}
			
		} else {
			Controller.controlSinThread();
			Controller.control();
		}
		
		if ((Gdx.input.isKeyJustPressed(Keys.C)) && (!isMenuOpened)) {
			isMenuOpened = true;
		}
		
		if (startBattle) {
			game.empezarBatalla();
		}
		
		batch.end();
	}
	
	public enum menuSelection {
		Cerrar,
		Equipamiento,
		Salir
	}
	
	public void battleRandomizer() {
		int chance = (int) ((Math.random() * (10 - 0)) + 0);
		if (chance > 5) {
			game.empezarBatalla();
		}
	}
	
	public void cargarTienda() {
		game.getScreen().dispose();
		game.setScreen(new ShopScene(game));
	}

	@Override
	public void dispose() {
		game.setX(player.getX());
		game.setY(player.getY());
		GestionMapa.guardarfichero(map.getCeldas(), "mapa");
		partida.guardarpartida();
		map.dispose();
		gameMenu.dispose();
		menuFont.dispose();
		map.getTileSet().dispose();
		map.getShop().dispose();
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
