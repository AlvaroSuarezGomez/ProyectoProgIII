package com.monsterfantasy.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.monsterfantasy.game.battle.BaseDeDatos;
import com.monsterfantasy.game.battle.Consumible;
import com.monsterfantasy.game.battle.Equipacion;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Objeto;
import com.monsterfantasy.game.battle.Pociones;
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
	
	private float elapsedTime = 0;
	private String objectiveText = "";
	private String initialText = "";
	private int index;
	float letterDelay = 0.05f;
	
	ArrayList<String> phrases = new ArrayList<>();
	private int phrase_id;
	
	ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	private int selectedItem = 0;
	
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
		for (Objeto o : BaseDeDatos.getPociones()) {
			objetos.add(o);
		}
		for (Objeto o : BaseDeDatos.getEquipaciones()) {
			objetos.add(o);
		}
		
		
		phrases = new ArrayList<>(Arrays.asList("Buf, ayer me comi \nunas croquetas de muerte.", 
				"Una vez cree un deporte \njunto a Gerard Pique \ny lo peto. \nQue bonitos eran \nlos torneos de globos.", 
				"Cuando todavia ni \nexistia TikTok yo narraba \nlos torneos del LoL. \nBuenos tiempos aquellos.",
				"Si ves por ahi \na Messi o al Kun saludalos \nde mi parte.",
				"Ramon Garcia es super \nbuena gente, ya he \ndado las campanadas \ncon el un par de \nNocheviejas.",
				"Lo reconozco, soy Ampeter.",
				"Despidete bien no?",
				"Lorem ipsum dolor sit amet, \nconsectetur adipiscing elit, \nsed eiusmod tempor \nincidunt ut labore et dolore \nmagna aliqua. \nUt enim ad minim veniam, \nquis nostrud exercitation \nullamco laboris nisi ut aliquid \nex ea commodi consequat."));
		
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
		shopFont.draw(batch, text, 15, 280);
	
		
		if (menuMode == menu.Principal) {
			objectiveText = "Bienvenido a la tienda del \nGigante Noble.";
			escribirTexto(Gdx.graphics.getDeltaTime());
			shopFont.draw(batch, "COMPRAR", 600, 250);
			shopFont.draw(batch, "HABLAR", 600, 150);
			shopFont.draw(batch, "SALIR", 600, 50);
			seleccionMenu();
		} else if (menuMode == menu.Comprar) {
			shopFont.draw(batch, "Dinero: " + heroe.getDinero() + "G", 600, 50);
			shopFont.draw(batch, "Precio: " + String.valueOf(objetos.get(selectedItem).getPrecio()) + "G", 600, 250);
			shopFont.draw(batch, objetos.get(selectedItem).getNombre(), 25, 50);
			if (objetos.get(selectedItem).getClass() == Pociones.class) {
			shopFont.draw(batch, "Recupera " + String.valueOf(((Pociones) objetos.get(selectedItem)).getPuntossalud()) + " PV", 250, 50);
			} else if (objetos.get(selectedItem).getClass() == Equipacion.class) {
				shopFont.draw(batch, "+" + String.valueOf(((Equipacion) objetos.get(selectedItem)).getPuntosdefensa()) + " Puntos Defensa", 200, 50);
			}
			escribirTexto(Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyJustPressed(Keys.X)) {
				index = 0;
				menuMode = menu.Principal;
			} else if ((Gdx.input.isKeyJustPressed(Keys.D)) && (selectedItem < (objetos.size()-1))) {
				selectedItem += 1;
			} else if ((Gdx.input.isKeyJustPressed(Keys.A)) && (selectedItem > 0)) {
				selectedItem -= 1;
			} else if ((Gdx.input.isKeyJustPressed(Keys.Z)) && (heroe.getDinero() >= objetos.get(selectedItem).getPrecio())) {
				if (objetos.get(selectedItem).getClass() == Pociones.class) {
				heroe.getPociones().add((Pociones) objetos.get(selectedItem));
				heroe.setDinero(heroe.getDinero() - objetos.get(selectedItem).getPrecio());
				} else if (objetos.get(selectedItem).getClass() == Equipacion.class) {
					heroe.getEquipacion().add((Equipacion) objetos.get(selectedItem));
					heroe.setDinero(heroe.getDinero() - objetos.get(selectedItem).getPrecio());
					}
			}
			
		} else if (menuMode == menu.Hablar) {
			objectiveText = phrases.get(phrase_id);
			escribirTexto(Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyJustPressed(Keys.Z)) {
				index = 0;
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
				index = 0;
				objectiveText = "Claro, dime que quieres \ncomprar";
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
				index = 0;
				randomizadorFrases();
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
	
	public void escribirTexto(float delta) {
		  elapsedTime += delta;
		  if (elapsedTime >= letterDelay) {
		  	if (objectiveText != initialText) {
				  initialText = objectiveText.substring(0, index);
				  index++;
				  elapsedTime -= letterDelay;    
			  }
			    text = initialText;
			}
	}
	
	public void randomizadorFrases() {
		Random random = new Random();
		phrase_id = random.nextInt((phrases.size()-1) + 1);
	}
}
