package com.monsterfantasy.game.battle;

public class Heroe extends Personaje {


	/**
	 * Dinero acumulado que tiene nuestro heroe
	 * 
	 */
	protected int dinero;
	/**
	 * Experiencia del heroe
	 * 
	 */
	protected int exp;
	/**
	 * Nivel en el que se encuentra
	 */
	protected int nv;

	/**
	 * @return Puntos de experiencia del Heroe
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Modifica el valor de los puntos de experiencia del heroe
	 * 
	 * @param exp Puntos de experiencia del Heroe a asignar
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * @return Nivel del heroe
	 */
	public int getNv() {
		return nv;
	}

	/**
	 * Modifica el nivel del heroe
	 * 
	 * @param nv nivel del heroe a asignar
	 */
	public void setNv(int nv) {
		this.nv = nv;
	}

	/**
	 * @return Lista de pociones en el inventario del heroe
	 */

	/**
	 * @return Dinero del heroe
	 */
	public int getDinero() {
		return dinero;
	}

	/**
	 * Modifica el dinero del heroe
	 * 
	 * @param dinero Dinero del heroe a asignarle
	 */
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	

	/**
	 * Constructor
	 * 
	 * @param pv              Puntos de vida actuales
	 * @param pvmax           Puntos de vida maximos
	 * @param ataque          Puntos de ataque
	 * @param defensa         puntos de defensa
	 * 
	 * @param espiritu        Puntos de espiritu que sirven para realizar ataques
	 *                        especiales
	 * @param dinero          Dinero que tiene acumulado el heroe
	 * @param exp             Experiencia del heroe
	 * @param nv              Nivel en el que se encuentra
	 * @param posicionguardia Booleano que determina si se encuentra el luchador en
	 *                        posicion de defensa
	 */
	public Heroe(int pv, int pvmax, int ataque, int defensa,  int dinero, int exp, int nv,
			boolean posicionguardia) {
		super(pv, pvmax, ataque, defensa, posicionguardia);

		this.dinero = dinero;
		this.exp = exp;
		this.nv = nv;

	}

	

	/**
	 * Usa una pocion para ayudar al heroe en combate
	 * 
	 */
	public void usaobjeto() {

	}

	/**
	 * El heroe realiza un ataque sobre el enemigo
	 *
	 */
	public void ataque(Personaje p) {
		Enemigo enemigo = (Enemigo) p;
		int danyo;
		if (enemigo.posicionguardia == false) {
			danyo = this.ataque - enemigo.defensa;
			if (danyo <= 0) {
				danyo = 1;
				enemigo.setPv(enemigo.getPv() - danyo);
			}

			else {
				enemigo.setPv(enemigo.getPv() - danyo);
			}
		} else {
			danyo = this.ataque - enemigo.defensa * 2;
			if (danyo <= 0) {
				danyo = 1;
				enemigo.setPv(enemigo.getPv() - danyo);
				enemigo.setPosicionguardia(false);
			} else {
				enemigo.setPv(enemigo.getPv() - danyo);
			}

		}

	}

	/**
	 * El heroe adopta una posicion de guardia
	 *
	 */
	public void guardia() {
		this.posicionguardia = true;

	}

	/**
	 * Huye del combate
	 * 
	 */
	public void huir() {
		System.out.println("Has podido escapar");

	}

}












