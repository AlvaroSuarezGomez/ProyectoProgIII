package com.monsterfantasy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;

public class ShopScene extends ScreenAdapter{
	private SpriteBatch batch;
	private Monsterfantasy game;
	private Texture background;
	private Texture pointer;
	private Heroe heroe;
	private Partida partida;
	private Music music;
	private String text = "";
	private menu menuMode = menu.Principal;
	private seleccion selectedOption = seleccion.Comprar;
	private BitmapFont shopFont;
	
	public ShopScene(Monsterfantasy game) {
		super();
		this.game = game;
		background = new Texture("TIENDA DEL GIGANTE NOBLE.png");
		pointer = new Texture("Pointer.png");
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		music = Gdx.audio.newMusic(Gdx.files.internal("hip_shop.ogg"));
		music.setLooping(true);
		shopFont = new BitmapFont(Gdx.files.internal("shop.fnt"), Gdx.files.internal("shop.png"), false);
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		music.play();
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		game.getCam().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		game.getCam().update();
		batch.draw(background, 0, 0, 800, 600);
		shopFont.draw(batch, text, 50, 250);
	
		
		if (menuMode == menu.Principal) {
			text = "Bienvenido a la tienda del \nGigante Noble";
			shopFont.draw(batch, "COMPRAR", 600, 250);
			shopFont.draw(batch, "HABLAR", 600, 150);
			shopFont.draw(batch, "SALIR", 600, 50);
			seleccionMenu();
		} else if (menuMode == menu.Comprar) {
			text = "Claro, dime que quieres \ncomprar";
			if (Gdx.input.isKeyJustPressed(Keys.X)) {
				menuMode = menu.Principal;
			}
		} else if (menuMode == menu.Hablar) {
			text = "Buf, ayer me comi \nunas croquetas de muerte";
			if (Gdx.input.isKeyJustPressed(Keys.Z)) {
				menuMode = menu.Principal;
			}
		}
		
		
		batch.end();
	}

	@Override
	public void dispose() {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		music.dispose();
		background.dispose();
		pointer.dispose();
		shopFont.dispose();
		super.dispose();
	}
	
	public enum menu {
		Principal,
		Comprar,
		Hablar
	}
	
	public enum seleccion {
		Comprar,
		Hablar,
		Salir
	}
	
	private void seleccionMenu() {
		if (selectedOption == seleccion.Comprar) {
			batch.draw(pointer, 550, 225, 25, 25);
			if (Gdx.input.isKeyJustPressed(Keys.S)) {
				selectedOption = seleccion.Hablar;
			}
			else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
				menuMode = menu.Comprar;
			}
		}
		
		
		if (selectedOption == seleccion.Hablar) {
			batch.draw(pointer, 550, 125, 25, 25);
			if (Gdx.input.isKeyJustPressed(Keys.S)) {
				selectedOption = seleccion.Salir;
			} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
				selectedOption = seleccion.Comprar;
			}
			
			else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
				menuMode = menu.Hablar;
			}
		}
		
		
		if (selectedOption == seleccion.Salir) {
			batch.draw(pointer, 550, 25, 25, 25);
			if (Gdx.input.isKeyJustPressed(Keys.W)) {
				selectedOption = seleccion.Hablar;
			} 
			
			else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
				game.getScreen().dispose();
				game.setScreen(new OverworldScene(game));
			}
		}
	}
	
	
	
		
}
