package com.monsterfantasy.game.battle;



public abstract class Personaje {


	/**
	 * Puntos de vida actuales
	 * 
	 */
	protected int pv;
	/**
	 * Puntos de vida maximos
	 * 
	 */
	protected int pvmax;
	/**
	 * Puntos de ataque
	 * 
	 */
	protected int ataque;
	/**
	 * puntos de defensa
	 * 
	 */
	protected int defensa;

	/**
	 * Puntos de espiritu que sirven para realizar ataques especiales
	 * 
	 */
	protected int espiritu;
	
	
	
	public int getEspiritu() {
		return espiritu;
	}

	public void setEspiritu(int espiritu) {
		this.espiritu = espiritu;
	}

	/**
	 * Booleano que determina si se encuentra el luchador en posicion de defensa
	 * 
	 */
	protected boolean posicionguardia;

	/**
	 * @return Booleano que determina si se encuentra el luchador en posicion de
	 *         defensa
	 */
	public boolean isPosicionguardia() {
		return posicionguardia;
	}

	/**
	 * Modifica el Booleano que determina si se encuentra el luchador en posicion de
	 * defensa
	 * 
	 * @param posicionguardia Booleano que determina si se encuentra el luchador en
	 *                        posicion de defensa a asignar
	 */
	public void setPosicionguardia(boolean posicionguardia) {
		this.posicionguardia = posicionguardia;
	}

	/**
	 * Constructor
	 * 
	 * @param pv              Puntos de vida actuales
	 * @param pvmax           Puntos de vida maximos
	 * @param ataque          Puntos de ataque
	 * @param defensa         puntos de defensa
	 * @param posicionguardia Booleano que determina si se encuentra el luchador en
	 *                        posicion de defensa
	 */
	public Personaje(int pv, int pvmax, int ataque, int defensa, boolean posicionguardia, int espiritu) {
		this.ataque = ataque;
		this.pv = pv;
		this.pvmax = pvmax;
		this.defensa = defensa;
		this.posicionguardia = false;
		this.espiritu = espiritu;
	}

	/**
	 * @return Puntos de vida actuales
	 */
	public int getPv() {
		return pv;
	}

	/**
	 * Modifica los Puntos de vida actuales
	 * 
	 * @param pv Puntos de vida actuales a asignar
	 */
	public void setPv(int pv) {
		this.pv = pv;
	}

	/**
	 * @return Puntos de vida maximos
	 */
	public int getPvmax() {
		return pvmax;
	}

	/**
	 * Modifica los Puntos de vida maximos
	 * 
	 * @param pvmax Puntos de vida maximos a asignar
	 */
	public void setPvmax(int pvmax) {
		this.pvmax = pvmax;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	/**
	 * @return Puntos de defensa
	 */
	public int getDefensa() {
		return defensa;
	}

	/**
	 * Modifica los Puntos de defensa
	 * 
	 * @param defensa Puntos de defensa a asignar
	 */
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	/**
	 * ataque que realiza un luchador sobre otro
	 * 
	 * @param p
	 */
	public abstract void ataque(Personaje p);

	/**
	 * El luchador adopta la posicion de defensa para recibir la mitad de danyo 
	 * 
	 */
	public abstract void guardia();

	public boolean equals(Personaje p) {
		if (this.pvmax == p.pvmax) {
			return true;
		}
		return false;
	}

}
