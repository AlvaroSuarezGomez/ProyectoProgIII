package com.monsterfantasy.game.battle;

import java.io.Serializable;

public class Equipacion extends Objeto implements Equipable , Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Puntos de defensa adicionales que te proporciona equipar el objeto 
	 */
	private int puntosdefensa;
	

	

	public Equipacion(int puntosdefensa, String nombre, int precio) {
		super(nombre,precio);
		this.setPuntosdefensa(puntosdefensa);
	}

	@Override
	public void equipar(Heroe h) {
		h.setDefensa(h.getDefensa() + this.getPuntosdefensa());
		h.getEquipacion().add(this);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desequipar(Heroe h) {
		// TODO Auto-generated method stub
		h.setDefensa(h.getDefensa() - this.getPuntosdefensa());
		h.getEquipacion().remove(this);
		
	}

	public int getPuntosdefensa() {
		return puntosdefensa;
	}

	public void setPuntosdefensa(int puntosdefensa) {
		this.puntosdefensa = puntosdefensa;
	}
	
	

}
