package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Overworld extends Actor {
	private static final int spawnPoint_x = 0;
	private static final int spawnPoint_y = 0;
	private static final int tamano_celda = 64;
	private static final int ancho_mapa = 10000;
	private static final int alto_mapa = 10000;
	
	private static final int tamano_textura = 64;
	
	
	private Texture tileSet;
	private Texture personaje;
	private TextureRegion suelo;
	private TextureRegion arbol;
	private TextureRegion hierba;
	
	public Overworld() {
        
		tileSet = new Texture("Overworld tileset.png");
		
		setWidth(ancho_mapa);
		setHeight(alto_mapa);
	}

	public static int getSpawnpointY() {
		return spawnPoint_y;
	}

	public static int getSpawnpointX() {
		return spawnPoint_x;
	}

	public TextureRegion getSuelo() {
		return suelo;
	}

	public void setSuelo(TextureRegion suelo) {
		this.suelo = suelo;
	}
}
