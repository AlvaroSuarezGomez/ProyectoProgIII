package com.monsterfantasy.game.overworld;

public class Celda {
	private int fila;
	private int columna;
	
	public Celda(int fila, int columna) {
		this.setFila(fila);
		this.setColumna(columna);
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
}
