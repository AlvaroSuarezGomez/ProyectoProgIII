package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Overworld extends Actor {
	private static final int tamano_celda = 40;
	private static final int ancho_mapa = 10000;
	private static final int alto_mapa = 10000;
	
	private static final int tamano_textura = 16;
	
	private Texture tileSet;
	private Texture personaje;
	private TextureRegion suelo;
	private TextureRegion arbol;
	
	public Overworld() {
        
		tileSet = new Texture("Overworld tileset.png");
		suelo = getRegion(tileSet, 40, 50);
		
		
	}
	private TextureRegion getRegion(Texture texture, int row, int column) {
		return new TextureRegion(texture, tamano_textura * (column - 1), tamano_textura * (row - 1), tamano_textura, tamano_textura);
	}
}
