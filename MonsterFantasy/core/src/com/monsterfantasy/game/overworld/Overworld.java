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
	
	
	private static Texture tileSet;
	private static Texture personaje;
	private static TextureRegion suelo;
	private static TextureRegion arbol;
	private static TextureRegion hierba;
	
	public Overworld() {
        
		setTileSet(new Texture("Overworld tileset.png"));
		setSuelo(new TextureRegion(getTileSet(), 0, 0, 64, 64));
		
		setWidth(getAnchoMapa());
		setHeight(getAltoMapa());
	}

	public static int getSpawnpointY() {
		return spawnPoint_y;
	}

	public static int getSpawnpointX() {
		return spawnPoint_x;
	}

	public static int getAnchoMapa() {
		return ancho_mapa;
	}

	public static int getAltoMapa() {
		return alto_mapa;
	}

	public static TextureRegion getSuelo() {
		return suelo;
	}

	public static void setSuelo(TextureRegion suelo) {
		Overworld.suelo = suelo;
	}

	public static Texture getTileSet() {
		return tileSet;
	}

	public static void setTileSet(Texture tileSet) {
		Overworld.tileSet = tileSet;
	}
}
