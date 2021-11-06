package com.monsterfantasy.game.battle;

public class Enemigo extends Personaje {



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
			String nombre) {
		super(pv, pvmax, ataque, defensa, posicionguardia);
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
	 * El enemigo adopta una posicion de defensa
	 */
	public void guardia() {
		this.posicionguardia = true;

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









