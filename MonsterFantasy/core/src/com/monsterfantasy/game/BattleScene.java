package com.monsterfantasy.game;

import java.io.File;
import java.util.ArrayList;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.monsterfantasy.game.battle.BaseDeDatos;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.Overworld;

public class BattleScene extends ScreenAdapter {
	private SpriteBatch batch;
	private Monsterfantasy game;
	private Texture background;
	private Texture hero_text;
	private TextureRegion hero_region;
	private Texture enemy_text;
	private Texture hero_bar;
	private Texture enemy_bar;
	private Texture life;
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
	
	public BattleScene(Monsterfantasy game) {
		super();
		this.game = game;
		enemigos = BaseDeDatos.getEnemigos();
		background = new Texture("Battle Background.png");
		hero_text = new Texture("Hero.png");
		hero_bar = new Texture("HeroBar.png");
		hero_region = new TextureRegion(hero_text, 175, 234);
		enemy_bar = new Texture("EnemyBar.png");
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		name = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		name.getData().setScale(1.5f, 1.5f);
		hp = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		hp.getData().setScale(0.7f, 0.7f);
		life = new Texture("HP.png");
		hp_container = new NinePatch(life,0,0,0,0);
		
		for (Enemigo e : enemigos) {
			if (e.getNombre().equals("WarGreymon")) {
				enemigo = e;
			}
		}
		enemy_text = new Texture(Gdx.files.internal("enemigos/" + enemigo.getNombre() + ".png"));
		song = Gdx.audio.newMusic(Gdx.files.internal("Provisional Battle Music.mp3"));
		song.setLooping(true);
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		song.play();
		enemyHP_width = (160f*enemigo.getPv())/enemigo.getPvmax();
		playerHP_width = (160f*heroe.getPv())/heroe.getPvmax();
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		game.getCam().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		game.getCam().update();
		batch.draw(background, 0, 0);
		batch.draw(hero_bar, 386, 128, 416, 160);
		batch.draw(enemy_bar, 0, 400, 416, 160);
		batch.draw(hero_region, 140, 124, 192, 257);
		batch.draw(enemy_text, 450, 300);
		name.draw(batch, game.getPartida().getNombre(), 600, 250);
		name.draw(batch, "Lvl. " + String.valueOf(game.getHeroe().getNv()), 700, 200);
		name.draw(batch, enemigo.getNombre(), 75, 525);
		hp.draw(batch, String.valueOf(heroe.getPv()) + "/" + String.valueOf(heroe.getPvmax()), 525f, 172.5f);
		hp_container.draw(batch, 490, 184, playerHP_width, 10);
		hp_container.draw(batch, 185, 456, enemyHP_width, 10);
		
		if (Gdx.input.isKeyPressed(Keys.Z)) {
			heroe.ataque(enemigo);
			enemigo.ataque(heroe); 
			}
		
		if (enemigo.getPv() <= 0) {
			heroe.setExp(heroe.getExp() + enemigo.getExprecompensa());
			game.getScreen().dispose();
			game.setScreen(new OverworldScene(game));
		}
		
		if (heroe.getPv() <= 0) {
			heroe.setPv(heroe.getPvmax());
			game.setScreen(new OverworldScene(game));
		}
		
		batch.end();
	}

	@Override
	public void dispose() {
		partida.guardarpartida();
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
		song.dispose();
		background.dispose();
		hero_text.dispose();
		hero_bar.dispose();
		enemy_bar.dispose();
		name.dispose();
		hp.dispose();
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
}
