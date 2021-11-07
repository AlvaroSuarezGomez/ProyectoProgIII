package com.monsterfantasy.game.battle;

public class Enemigo extends Personaje {


	
	/**
	 * Puntos de espiritu para realizar ataques especiales 
	 */
	protected int espiritu; 
	
	
	public int getEspiritu() {
		return espiritu;
	}

	public void setEspiritu(int espiritu) {
		this.espiritu = espiritu;
	}

	/**
	 * Puntos de experiencia que obtiene el heroe al derrotar al enemigo
	 * 
	 */
	protected int exprecompensa;

	/**
	 *  Nombre del enemigo
	 */
	protected String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Puntos de experiencia que obtiene el heroe al derrotar al enemigo
	 */
	public int getExprecompensa() {
		return exprecompensa;
	}

	/**
	 * Modifica los Puntos de experiencia que obtiene el heroe al derrotar al
	 * enemigo
	 * 
	 * @param exprecompensa Puntos de experiencia que obtiene el heroe al derrotar
	 *                      al enemigo a asignar
	 */
	public void setExprecompensa(int exprecompensa) {
		this.exprecompensa = exprecompensa;
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
	 * @param exprecompensa   Puntos de experiencia que obtiene el heroe al derrotar
	 *                        al enemigo
	 */
	public Enemigo(int pv, int pvmax, int ataque, int defensa, boolean posicionguardia, int exprecompensa,
			String nombre, int espiritu) {
		super(pv, pvmax, ataque, defensa, posicionguardia, espiritu);
		this.exprecompensa = exprecompensa;
		this.nombre = nombre;

	}

	/**
	 * El enemigo realiza un ataque sobre el heroe
	 */
	public void ataque(Personaje p) {
		Heroe heroe = (Heroe) p;
		int danyo;
		if (heroe.posicionguardia == false) {
			danyo = this.ataque - heroe.defensa;
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
			}

			else {
				heroe.setPv(heroe.getPv() - danyo);
			}
		} else {
			danyo = this.ataque - (heroe.defensa * 2);
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
				heroe.setPosicionguardia(false);
			}

			else {
				heroe.setPv(heroe.getPv() - danyo);
			}
		}

	}
	
	
	/**
	 * @param p Personaje al que se ataca
	 * @param at ataque especial que se realiza
	 */
	public void ataqueespecial(Personaje p, AtaqueEspecial at) {
		Heroe heroe = (Heroe) p;
		int danyo;
		if (heroe.posicionguardia == false) {
			danyo = this.ataque + at.getPotencia() - heroe.defensa;
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
			}

			else {
				heroe.setPv(heroe.getPv() - danyo);
			}
		} else {
			danyo = this.ataque + at.getPotencia() - heroe.defensa * 2;
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
				heroe.setPosicionguardia(false);
			} else {
				heroe.setPv(heroe.getPv() - danyo);
			}

		}

	}

	/**
	 * El enemigo adopta una posicion de defensa y se le suman dos puntos de espiritu
	 */
	public void guardia() {
		this.posicionguardia = true;
		this.espiritu = this.espiritu + 2;

	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public boolean equals(Personaje p) {
		Enemigo e = (Enemigo) p;
		if (this.pvmax == e.pvmax) {
			return true;
		} else {
			return false;
		}
	}

}








