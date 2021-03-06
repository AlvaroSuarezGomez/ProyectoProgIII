package com.monsterfantasy.game.overworld;

import java.io.Serializable;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.monsterfantasy.game.Monsterfantasy;
import com.monsterfantasy.game.OverworldScene;

public class Overworld extends Actor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 261615780511554392L;
	
	private final int tamano_celda = 64;
	private final int ancho_mapa = 100 * 64;
	private final int alto_mapa = 100 * 64;
	private final int spawnPoint_x = getAncho_mapa()/2;
	private final int spawnPoint_y = getAlto_mapa()/2;
	private Celda[][] celdas = new Celda[getAnchoMapa()/64][getAltoMapa()/64];
	
	private static final int tamano_textura = 64;
	
	
	private Texture tileSet;
	private TextureRegion suelo;
	private TextureRegion arbol;
	private TextureRegion hierba;
	private Texture shop;
	private TextureRegion tienda;
	
	public Overworld() {	
		setWidth(getAnchoMapa());
		setHeight(getAltoMapa());
	}

	public int getSpawnpointY() {
		return spawnPoint_y;
	}

	public int getSpawnpointX() {
		return spawnPoint_x;
	}

	public int getAnchoMapa() {
		return getAncho_mapa();
	}

	public int getAltoMapa() {
		return getAlto_mapa();
	}

	public TextureRegion getSuelo() {
		return suelo;
	}

	public void setSuelo(TextureRegion suelo) {
		this.suelo = suelo;
	}
	
	public TextureRegion getArbol() {
		return arbol;
	}

	public void setArbol(TextureRegion arbol) {
		this.arbol = arbol;
	}

	public Texture getTileSet() {
		return tileSet;
	}

	public void setTileSet(Texture tileSet) {
		this.tileSet = tileSet;
	}
	
	public void crearCeldas() {
		for (int row = 0; row < getFilas(); row++) {
			for (int column = 0; column < getColumnas(); column++) {
				celdas[row][column] = new Celda(row, column, TipoCelda.Suelo);
			}
		}
	}
	
	@Override
    public void draw(Batch batch, float parentAlpha) {
		for (int row = 0; row < getFilas(); row++) {
			for (int column = 0; column < getColumnas(); column++) {
				float x = celdas[row][column].getX();
				float y = celdas[row][column].getY();
				batch.draw(suelo, 
					x, y, getOriginX(), getOriginY(), 
					tamano_celda, tamano_celda, getScaleX(), getScaleY(), getRotation()
				);

				TextureRegion content = null;			
				switch (celdas[row][column].getTipo()) {
					case Suelo:	content = suelo;
									break;

					case Arbol:		content = arbol;
									break;

					case Hierba:		content = hierba;
									break;
									
					case Tienda:			content = tienda;
									break;

					default: 		break;
				}
					batch.draw(content, 
						x, y, getOriginX(), getOriginY(), 
						tamano_celda, tamano_celda, getScaleX(), getScaleY(), getRotation()
					);
				}
			}
		}
	
	public void dispose() {
		suelo.getTexture().dispose();
		hierba.getTexture().dispose();
		arbol.getTexture().dispose();
		tileSet.dispose();
	}
	
	public Celda[][] getCeldas() {
		return celdas;
	}

	public void setCeldas(Celda[][] celdas) {
		this.celdas = celdas;
	}
	
	public int getFilas() {
		return getCeldas().length;
	}
	
	public int getColumnas() {
		return getCeldas()[0].length;
	}

	public TextureRegion getHierba() {
		return hierba;
	}

	public void setHierba(TextureRegion hierba) {
		this.hierba = hierba;
	}

	public TextureRegion getTienda() {
		return tienda;
	}

	public void setTienda(TextureRegion tienda) {
		this.tienda = tienda;
	}

	public Texture getShop() {
		return shop;
	}

	public void setShop(Texture shop) {
		this.shop = shop;
	}

	public int getAlto_mapa() {
		return alto_mapa;
	}

	public int getAncho_mapa() {
		return ancho_mapa;
	}
}
