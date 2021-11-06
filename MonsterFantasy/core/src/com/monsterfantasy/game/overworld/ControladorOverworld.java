package com.monsterfantasy.game.overworld;

public class ControladorOverworld {
	private int filasOverworld = 10;
	private TipoCelda[][] dataOverworld = new TipoCelda[getFilasOverworld()][];
	
	private Celda jugador = new Celda(0,0);

	public int getFilasOverworld() {
		return filasOverworld;
	}
	public int getColumnasOverworld() {
		return dataOverworld[0].length;
	}
	public TipoCelda getTipoCelda(Celda celda) {
		return dataOverworld[celda.getFila()][celda.getColumna()];	
	}
	private void setTipoCelda(Celda celda, TipoCelda tipoCelda) {
		dataOverworld[celda.getFila()][celda.getColumna()] = tipoCelda;
		}
}
