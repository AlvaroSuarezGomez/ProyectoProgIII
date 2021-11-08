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
		setSuelo(getRegion(tileSet, 40, 50));
		
		setWidth(ancho_mapa);
		setHeight(alto_mapa);
	}
	
	private float columnaX(int columna) {
		return columna * tamano_celda + getX();
	}
	
	private float filaY(int fila) {
		return alto_mapa - 1 - (tamano_celda * (fila + 1)) + getY();
	}
	
	public void draw(Batch batch, float parentAlpha) {
		for (int x = 0; x < ancho_mapa; x++) {
			for (int y = 0; y < alto_mapa; y++) {
				batch.draw(getSuelo(), 
						x, y, getOriginX(), getOriginY(), 
						tamano_celda, tamano_celda, getScaleX(), getScaleY(), getRotation()
						);
				
		}
		}
	}
	
	
	private TextureRegion getRegion(Texture texture, int row, int column) {
		return new TextureRegion(texture, tamano_textura * (column - 1), tamano_textura * (row - 1), tamano_textura, tamano_textura);
	}
	
	public void dispose() {		
		getSuelo().getTexture().dispose();
		arbol.getTexture().dispose();
		hierba.getTexture().dispose();
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
