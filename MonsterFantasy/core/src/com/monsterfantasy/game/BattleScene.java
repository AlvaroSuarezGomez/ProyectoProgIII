package com.monsterfantasy.game;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.FileHandler;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.monsterfantasy.game.battle.AtaqueEspecial;
import com.monsterfantasy.game.battle.BaseDeDatos;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;
import com.monsterfantasy.game.utilities.GifDecoder;

import Test.BaseDeDatosTest;

public class BattleScene extends ScreenAdapter {
	private int selectedSpecialAttack = 0;
	private int selectedItem = 0;
	private comandos selectedCommand;
	private interfaz UI;
	private SpriteBatch batch;
	private Monsterfantasy game;
	private Texture background;
	private Texture hero_text;
	private TextureRegion hero_region;
	private Texture enemy_text;
	private Texture hero_bar;
	private Texture enemy_bar;
	private Texture life;
	private Texture attackButton;
	private Texture specialAttackButton;
	private Texture guardButton;
	private Texture objectButton;
	private Texture selectorButton;
	private BitmapFont combatText;
	private BitmapFont name;
	private BitmapFont hp;
	private Heroe heroe;
	private Music song;
	private Enemigo enemigo;
	private Partida partida;
	private ArrayList<Enemigo> enemigos;
	NinePatch hp_container; 
	private float playerHP_width;
	private float enemyHP_width;
	private boolean canAttack = true;
	private int playerHP;
	private int enemyHP;
	private Animation<TextureRegion> rickRolled;
	float elapsed;
	
	public BattleScene(Monsterfantasy game) {
		super();
		this.game = game;
		enemigos = BaseDeDatos.getEnemigos();
		background = new Texture("Battle Background.png");
		hero_text = new Texture("Hero.png");
		hero_bar = new Texture("HeroBar.png");
		hero_region = new TextureRegion(hero_text, 175, 234);
		enemy_bar = new Texture("EnemyBar.png");
		attackButton = new Texture("AttackButton.png");
		specialAttackButton = new Texture("SpecialAttackButton.png");
		guardButton = new Texture("GuardButton.png");
		objectButton = new Texture("ObjectButton.png");
		selectorButton = new Texture("Selector.png");
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		name = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		name.getData().setScale(1.5f, 1.5f);
		hp = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		hp.getData().setScale(0.7f, 0.7f);
		combatText = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		combatText.getData().setScale(1.5f, 1.5f);
		life = new Texture("HP.png");
		hp_container = new NinePatch(life,0,0,0,0);
		playerHP = heroe.getPv();
		rickRolled = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("enemigos/RickRoll.gif").read());
		UI = interfaz.SeleccionComando;
		selectedCommand = comandos.Ataque;
		
		//PROVISIONAL, QUITAR EN ENTREGA FINAL
		heroe.getPociones().add(BaseDeDatos.getPociones().get(0));
		
		song = Gdx.audio.newMusic(Gdx.files.internal("Provisional Battle Music.mp3"));
		song.setLooping(true);
		
		Random r = new Random();
		int id = r.nextInt((enemigos.size()-1) + 1);
		enemigo = enemigos.get(id);
		
			if (enemigo.getNombre().equals("RickRoll")) {
				song.stop();
				song = Gdx.audio.newMusic(Gdx.files.internal("You have been rickrolled.mp3"));
		}
		
		
		try {
			if (enemigo.getNombre() != "RickRoll") {
			enemy_text = new Texture(Gdx.files.internal("enemigos/" + enemigo.getNombre() + ".png"));
			}
		} catch (GdxRuntimeException e) {
			enemy_text = new Texture(Gdx.files.internal("enemigos/MissingNo.png"));
		}
		
		enemyHP = enemigo.getPv();
		
	}
	
	@Override
	public void render(float dt) {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		song.play();
		
		//Dibujado elementos de interfaz
		enemyHP_width = (160f*enemyHP)/enemigo.getPvmax();
		playerHP_width = (160f*playerHP)/heroe.getPvmax();
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		game.getCam().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		game.getCam().update();
		batch.draw(background, 0, 0);
		batch.draw(hero_bar, 386, 128, 416, 160);
		batch.draw(enemy_bar, 0, 400, 416, 160);
		batch.draw(hero_region, 140, 124, 192, 257);
		
		if (enemigo.getNombre().equals("RickRoll"))  {
			batch.draw(rickRolled.getKeyFrame(elapsed), 450, 300);
		}
		else {
			batch.draw(enemy_text, 450, 300);
		}
		name.draw(batch, game.getPartida().getNombre(), 600, 250);
		name.draw(batch, "Lvl. " + String.valueOf(game.getHeroe().getNv()), 700, 200);
		name.draw(batch, enemigo.getNombre(), 75, 525);
		hp.draw(batch, String.valueOf(playerHP) + "/" + String.valueOf(heroe.getPvmax()), 500f, 172.5f);
		hp.draw(batch, "PE: " + String.valueOf(heroe.getEspiritu()), 600f, 172.5f);
		hp_container.draw(batch, 490, 184, playerHP_width, 10);
		hp_container.draw(batch, 185, 456, enemyHP_width, 10);
		
		//Menú de combate
		if (UI == interfaz.SeleccionComando) { 
		batch.draw(attackButton, 450, 65, 156, 44);
		batch.draw(guardButton, 610, 65, 156, 44);
		batch.draw(objectButton, 450, 15, 156, 44);
		batch.draw(specialAttackButton, 610, 15, 156, 44);
		combatText.draw(batch, "Selecciona un comando:", 20, 80);
		seleccionarComando();
		}
		
		else if (UI == interfaz.SeleccionAtaqueEspecial) {
			combatText.draw(batch, heroe.getAtaques().get(selectedSpecialAttack).getNombre(), 100, 80);
			combatText.draw(batch, "Requiere " + String.valueOf(heroe.getAtaques().get(selectedSpecialAttack).getEspiritu()) + " PE", 500, 80);
			
			if ((Gdx.input.isKeyJustPressed(Keys.D)) && selectedSpecialAttack < heroe.getAtaques().size()-1) {
				selectedSpecialAttack += 1;
			}
			
			else if ((Gdx.input.isKeyJustPressed(Keys.A)) && selectedSpecialAttack > 0) {
				selectedSpecialAttack -= 1;
			}
			
			else if (canAttack && (Gdx.input.isKeyJustPressed(Keys.Z)) && (heroe.getEspiritu() >= heroe.getAtaques().get(selectedSpecialAttack).getEspiritu())) {
				usarAtaqueEspecial();
			}
			
			else if (Gdx.input.isKeyJustPressed(Keys.X)) {
				UI = interfaz.SeleccionComando;
			}
			
		}
		
		else if (UI == interfaz.SeleccionObjeto) {
			combatText.draw(batch, heroe.getPociones().get(selectedItem).getNombre(), 100, 80);
			combatText.draw(batch, "Recupera " + String.valueOf(heroe.getPociones().get(selectedItem).getPuntossalud()) + " PV", 500, 80);
			
			if ((Gdx.input.isKeyJustPressed(Keys.D)) && selectedItem < heroe.getPociones().size()-1) {
				selectedItem += 1;
			}
			
			else if ((Gdx.input.isKeyJustPressed(Keys.A)) && selectedItem > 0) {
				selectedItem -= 1;
			}
			
			else if (canAttack && (Gdx.input.isKeyJustPressed(Keys.Z))) {
				usarObjeto();		
			}
			
			else if ((Gdx.input.isKeyJustPressed(Keys.X))) {
				UI = interfaz.SeleccionComando;
			}
		}
		
		
		//Finalizar combate
		if (enemyHP <= 0) {
			enemyHP = 0;
			heroe.setExp(heroe.getExp() + enemigo.getExprecompensa());
			game.getScreen().dispose();
			game.setScreen(new OverworldScene(game));
		}
		
		if (playerHP <= 0) {
			playerHP = 0;
			heroe.setPv(heroe.getPvmax());
			game.getScreen().dispose();
			game.setScreen(new OverworldScene(game));
		}
		
		batch.end();
	}
	
	public void bajarVidaJugador() {
			Thread barra = new Thread() {
			    public void run() {
			    	for (playerHP = playerHP; playerHP > heroe.getPv();) {
			    	try {
						Thread.sleep(10);
						playerHP--;
					}
			    	catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					} canAttack = true;  
			    	Thread.currentThread().interrupt(); 
			    }
			}; 
			barra.start();		
	}
	
	public void subirVidaJugador() {
		Thread barra = new Thread() {
		    public void run() {
		    	for (playerHP = playerHP; playerHP < heroe.getPv();) {
		    	try {
					Thread.sleep(10);
					playerHP++;
				}
		    	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				} canAttack = true;  
		    	Thread.currentThread().interrupt(); 
		    }
		}; 
		barra.start();		
}
	
	public void bajarVidaEnemigo() {
		Thread barra = new Thread() {
		    public void run() {
		    	for (enemyHP = enemyHP; enemyHP > enemigo.getPv();) {
		    	try {
					Thread.sleep(10);
					System.out.println(enemigo.getPv());
					System.out.println(enemyHP);
					enemyHP--;
				}
		    	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				}
		    	Thread.currentThread().interrupt(); 
		    }
		}; 
		barra.start();		
}
	
	
	
	public enum interfaz {
		SeleccionComando,
		SeleccionAtaqueEspecial,
		SeleccionObjeto,
		Texto
	}
	
	public enum comandos {
		Ataque,
		AtaqueEspecial,
		Guardia,
		Objeto
	}
	
	public void seleccionarComando() {
		if (selectedCommand == comandos.Ataque) {
			batch.draw(selectorButton, 450, 65, 156, 44);
			if (Gdx.input.isKeyJustPressed(Keys.D)) selectedCommand = comandos.Guardia;
			else if (Gdx.input.isKeyJustPressed(Keys.S)) selectedCommand = comandos.Objeto;
			
			else if (Gdx.input.isKeyJustPressed(Keys.Z) && (canAttack == true)) {
				atacar();
				}
			}
		
		
		else if (selectedCommand == comandos.Guardia) {
			batch.draw(selectorButton, 610, 65, 156, 44);
			if (Gdx.input.isKeyJustPressed(Keys.A)) selectedCommand = comandos.Ataque;
			else if (Gdx.input.isKeyJustPressed(Keys.S)) selectedCommand = comandos.AtaqueEspecial;
			
			else if (Gdx.input.isKeyJustPressed(Keys.Z) && (canAttack == true)) {
				guardia();
				}
			}
		
		else if (selectedCommand == comandos.Objeto) {
			batch.draw(selectorButton, 450, 15, 156, 44);
			if (Gdx.input.isKeyJustPressed(Keys.D)) selectedCommand = comandos.AtaqueEspecial;
			else if (Gdx.input.isKeyJustPressed(Keys.W)) selectedCommand = comandos.Ataque;
			
			else if ((Gdx.input.isKeyJustPressed(Keys.Z)) && heroe.getPociones().size() > 0) {
				UI = interfaz.SeleccionObjeto;
			}
		}
		
		else if (selectedCommand == comandos.AtaqueEspecial) {
			batch.draw(selectorButton, 610, 15, 156, 44);
			if (Gdx.input.isKeyJustPressed(Keys.A)) selectedCommand = comandos.Objeto;
			else if (Gdx.input.isKeyJustPressed(Keys.W)) selectedCommand = comandos.Guardia;	
			
			else if ((Gdx.input.isKeyJustPressed(Keys.Z)) && heroe.getAtaques().size() > 0) {
				UI = interfaz.SeleccionAtaqueEspecial;
			}	
		}
	}
	
	public void atacar() {
		Thread ataque = new Thread() {
		    public void run() {
		    	try {
		    		canAttack = false;
		    		heroe.setEspiritu(heroe.getEspiritu() + 1);
					heroe.ataque(enemigo);
					bajarVidaEnemigo();
					Thread.sleep(1000);
					enemigo.ataque(heroe); 
					bajarVidaJugador();
					Thread.sleep(1000);
					UI = interfaz.SeleccionComando;
				}
		    	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		    	Thread.currentThread().interrupt(); 
		    }
		}; 
		ataque.start();		
	}
	
	public void guardia() {
		Thread guardia = new Thread() {
		    public void run() {
		    	try {
		    		canAttack = false;
		    		heroe.guardia();
					Thread.sleep(1000);
					enemigo.ataque(heroe); 
					bajarVidaJugador();
					Thread.sleep(1000);
					UI = interfaz.SeleccionComando;
				}
		    	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		    	Thread.currentThread().interrupt(); 
		    }
		}; 
		guardia.start();	
	}
	
	public void usarObjeto() {
		Thread objeto = new Thread() {
		    public void run() {
		    	try {
		    		canAttack = false;
					heroe.getPociones().get(selectedItem).consumir(heroe);
					subirVidaJugador();
					Thread.sleep(1000);
					enemigo.ataque(heroe); 
					bajarVidaJugador();
					Thread.sleep(1000);
					UI = interfaz.SeleccionComando;
				}
		    	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		    	Thread.currentThread().interrupt(); 
		    }
		}; 
		objeto.start();			
	}
	
	public void usarAtaqueEspecial() {
		Thread ataqueEspecial = new Thread() {
		    public void run() {
		    	try {
		    		canAttack = false;
		    		heroe.setEspiritu(heroe.getEspiritu()-heroe.getAtaques().get(selectedSpecialAttack).getEspiritu());
		    		heroe.ataqueespecial(enemigo, heroe.getAtaques().get(selectedSpecialAttack));
		    		bajarVidaEnemigo();
					Thread.sleep(1000);
					enemigo.ataque(heroe); 
					bajarVidaJugador();
					Thread.sleep(1000);
					UI = interfaz.SeleccionComando;
				}
		    	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		    	Thread.currentThread().interrupt(); 
		    }
		}; 
		ataqueEspecial.start();			
	}

	@Override
	public void dispose() {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		song.dispose();
		background.dispose();
		hero_text.dispose();
		hero_bar.dispose();
		enemy_text.dispose();
		enemy_bar.dispose();
		name.dispose();
		hp.dispose();
		life.dispose();	
		super.dispose();
	}

	public Heroe getHeroe() {
		return heroe;
	}

	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}

	public Monsterfantasy getGame() {
		return game;
	}

	public void setGame(Monsterfantasy game) {
		this.game = game;
	}

	public boolean isCanAttack() {
		return canAttack;
	}

	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	public int getPlayerHP() {
		return playerHP;
	}

	public void setPlayerHP(int playerHP) {
		this.playerHP = playerHP;
	}
}
