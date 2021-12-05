package com.monsterfantasy.game.battle;

import java.util.ArrayList;

public class Heroe extends Personaje {

	
	
	protected ArrayList<AtaqueEspecial> ataques;
	
	protected ArrayList<Pociones> pociones;
	
	protected ArrayList<Equipacion> equipacion;

	public ArrayList<AtaqueEspecial> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<AtaqueEspecial> ataques) {
		this.ataques = ataques;
	}

	/**
	 * Dinero acumulado que tiene nuestro heroe.
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
	 * Puntos de espiritu para realizar ataques especiales 
	 */
	protected int espiritu;
	
	
	
	

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
			boolean posicionguardia, int espiritu) {
		super(pv, pvmax, ataque, defensa, posicionguardia, espiritu);

		this.dinero = dinero;
		this.exp = exp;
		this.nv = nv;
		
		this.pociones = new ArrayList<Pociones>();
		this.ataques = new ArrayList<AtaqueEspecial>();
		this.equipacion = new ArrayList<Equipacion>();
		this.espiritu = espiritu;

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
	 * El heroe adopta una posicion de guardia y se le suman dos puntos de espiritu
	 *
	 */
	public void guardia() {
		this.posicionguardia = true;
		this.espiritu = this.espiritu + 2;

	}


	
	
	
	/**
	 * @param p Personaje al que se ataca
	 * @param at ataque especial que se realiza
	 */
	public void ataqueespecial(Personaje p, AtaqueEspecial at) {
		Enemigo enemigo = (Enemigo) p;
		int danyo;
		if (enemigo.posicionguardia == false) {
			danyo = this.ataque + at.getPotencia() - enemigo.defensa;
			if (danyo <= 0) {
				danyo = 1;
				enemigo.setPv(enemigo.getPv() - danyo);
			}

			else {
				enemigo.setPv(enemigo.getPv() - danyo);
			}
		} else {
			danyo = this.ataque + at.getPotencia() - enemigo.defensa * 2;
			if (danyo <= 0) {
				danyo = 1;
				enemigo.setPv(enemigo.getPv() - danyo);
				enemigo.setPosicionguardia(false);
			} else {
				enemigo.setPv(enemigo.getPv() - danyo);
			}

		}

	}

	public ArrayList<Pociones> getPociones() {
		return pociones;
	}

	public void setPociones(ArrayList<Pociones> pociones) {
		this.pociones = pociones;
	}

	public ArrayList<Equipacion> getEquipacion() {
		return equipacion;
	}

	public void setEquipacion(ArrayList<Equipacion> equipacion) {
		this.equipacion = equipacion;
	}
	
	

	
}












