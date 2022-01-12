package com.monsterfantasy.game.overworld;

import java.io.Serializable;

public class Celda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8273076385010485951L;
	private int x;
	private int y;
	private int fila;
	private int columna;
	private TipoCelda tipo;
	
	public Celda(int fila, int columna, TipoCelda tipo) {
		this.setFila(fila);
		this.setColumna(columna);
		this.x = fila*64;
		this.y = columna*64;
		this.setTipo(tipo);
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TipoCelda getTipo() {
		return tipo;
	}

	public void setTipo(TipoCelda tipo) {
		this.tipo = tipo;
	}


}
