package com.monsterfantasy.game.battle;

public class Pociones extends Objeto implements Consumible{
	
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
