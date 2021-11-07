package com.monsterfantasy.game.battle;

public class AtaqueEspecial {

	
	/**
	 *  Nombre del ataque 
	 */
	private String nombre;
	
	/**
	 * daño adicional del ataque 
	 */
	private int potencia;

	/**
	 * Puntos de espiritu requeridos 
	 */
	private int espiritu;

	public AtaqueEspecial(String nombre, int potencia, int espiritu) {
		super();
		this.nombre = nombre;
		this.potencia = potencia;
		this.espiritu = espiritu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getEspiritu() {
		return espiritu;
	}

	public void setEspiritu(int espiritu) {
		this.espiritu = espiritu;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
