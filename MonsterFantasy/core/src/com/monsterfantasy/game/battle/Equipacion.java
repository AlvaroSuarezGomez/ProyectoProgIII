package com.monsterfantasy.game.battle;

import java.io.Serializable;

public class Equipacion extends Objeto implements Equipable , Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Puntos de defensa adicionales que te proporciona equipar el objeto 
	 */
	protected int puntosdefensa;
	

	

	public Equipacion(int puntosdefensa, String nombre, int precio) {
		super(nombre,precio);
		this.puntosdefensa = puntosdefensa;
	}

	@Override
	public void equipar(Heroe h) {
		h.setDefensa(h.getDefensa() + this.puntosdefensa);
		h.getEquipacion().add(this);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desequipar(Heroe h) {
		// TODO Auto-generated method stub
		h.setDefensa(h.getDefensa() - this.puntosdefensa);
		h.getEquipacion().remove(this);
		
	}
	
	

}
