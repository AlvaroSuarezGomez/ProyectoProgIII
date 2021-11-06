package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Overworld extends Actor {
	private static final int tamano_celda = 40;
	private static final int ancho_mapa = 10000;
	private static final int alto_mapa = 10000;
	
	private static final int tamano_textura = 16;
	
	private ControladorOverworld controlador;
	
	private Texture tileSet;
	private Texture personaje;
	private TextureRegion suelo;
	private TextureRegion arbol;
	private TextureRegion hierba;
	
	public Overworld(ControladorOverworld controlador) {
        this.controlador = controlador;
        
		tileSet = new Texture("Overworld tileset.png");
		suelo = getRegion(tileSet, 40, 50);
		
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
		for (int fila = 0; fila < controlador.getFilasOverworld(); fila++) {
			for (int columna = 0; columna < controlador.getColumnasOverworld(); columna++) {
				float x = columnaX(columna);
				float y = filaY(fila);
				batch.draw(suelo, 
						x, y, getOriginX(), getOriginY(), 
						tamano_celda, tamano_celda, getScaleX(), getScaleY(), getRotation()
						);
				TextureRegion content = null;
				switch (controlador.getTipoCelda(new Celda(fila, columna))) {
				case Suelo:	content = suelo;
							break;
							
				case Arbol: content = arbol;
							break;
				
				case Hierba: content = hierba;
							 break;
				}
				if (content != null) {
					batch.draw(content, 
						x, y, getOriginX(), getOriginY(), 
						tamano_celda, tamano_celda, getScaleX(), getScaleY(), getRotation()
					);
			}
		}
		}
	}
	
	
	private TextureRegion getRegion(Texture texture, int row, int column) {
		return new TextureRegion(texture, tamano_textura * (column - 1), tamano_textura * (row - 1), tamano_textura, tamano_textura);
	}
	
	public void dispose() {		
		suelo.getTexture().dispose();
		arbol.getTexture().dispose();
		hierba.getTexture().dispose();
	}
}
