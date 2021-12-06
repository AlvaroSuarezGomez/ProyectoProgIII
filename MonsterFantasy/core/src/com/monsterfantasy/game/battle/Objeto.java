package com.monsterfantasy.game.battle;

import java.io.Serializable;

public abstract class Objeto  implements Serializable {
	

	private static final long serialVersionUID = 1L;

	/**Nombre del objeto
	 * 
	 */
	protected String nombre;
	
	
	/** Precio del objeto
	 * 
	 */
	protected int precio;
	
	
	
	/**
	 * @return Nombre del objeto
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	/** Modifica el Nombre del objeto
	 * @param nombre   Nombre del objeto a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	/** 
	 * @return Precio del objeto
	 */
	public int getPrecio() {
		return precio;
	}
	
	
	/** modifica el Precio del objeto
	 * @param precio Precio del objeto a asignar
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	
	/** Constructor 
	 * @param nombre Nombre del objeto
	 * @param precio Precio del objeto
	 */
	public Objeto(String nombre, int precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	
	
	

}
