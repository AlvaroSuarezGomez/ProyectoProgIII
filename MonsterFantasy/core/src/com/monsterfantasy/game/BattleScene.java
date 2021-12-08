package com.monsterfantasy.game;

import java.io.File;
import java.util.logging.FileHandler;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	private BitmapFont hero_name;
	private BitmapFont hero_hp;
	private Heroe heroe;
	private Music song;
	private Enemigo enemigo;
	private Partida partida;
	
	public BattleScene(Monsterfantasy game) {
		super();
		this.game = game;
		background = new Texture("Battle Background.png");
		hero_text = new Texture("Hero.png");
		hero_bar = new Texture("HeroBar.png");
		hero_region = new TextureRegion(hero_text, 175, 234);
		enemy_bar = new Texture("EnemyBar.png");
		//enemy_text = new Texture("");
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		hero_name = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		hero_name.getData().setScale(1.5f, 1.5f);
		hero_hp = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		hero_hp.getData().setScale(0.7f, 0.7f);
		enemigo = new Enemigo(10, 10, 10, 10, false, 0, "WarGreymon", 0);
		song = Gdx.audio.newMusic(Gdx.files.internal("Provisional Battle Music.mp3"));
		song.setLooping(true);
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		song.play();
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		game.getCam().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		game.getCam().update();
		batch.draw(background, 0, 0);
		batch.draw(hero_bar, 386, 128, 416, 160);
		batch.draw(enemy_bar, 0, 400, 416, 160);
		batch.draw(hero_region, 140, 124, 192, 257);
		hero_name.draw(batch, game.getPartida().getNombre(), 600, 250);
		hero_name.draw(batch, "Lvl. " + String.valueOf(game.getHeroe().getNv()), 700, 200);
		hero_hp.draw(batch, String.valueOf(heroe.getPv()) + "/" + String.valueOf(heroe.getPvmax()), 525f, 172.5f);
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
		hero_name.dispose();
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
