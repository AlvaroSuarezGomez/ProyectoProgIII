package com.monsterfantasy.game.battle;

import java.io.Serializable;

public class Pociones extends Objeto implements Consumible, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Puntos de salud que recupera la pocion
	 */
	protected int puntossalud;

	public Pociones(String nombre, int precio, int puntossalud) {
		super(nombre, precio);
		this.puntossalud = puntossalud;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consumir(Heroe h) {
		// TODO Auto-generated method stub
		
		if(this.puntossalud + h.getPv() > h.getPvmax()) {
			h.setPv(h.getPvmax());
			h.getPociones().remove(this); 
		}
		
		else {
			h.setPv(this.puntossalud + h.getPv());
			h.getPociones().remove(this);
		}
	}
	
	

}
