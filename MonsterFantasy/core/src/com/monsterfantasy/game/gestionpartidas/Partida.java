package com.monsterfantasy.game.gestionpartidas;




import java.io.Serializable;
import java.util.logging.Logger;

import com.monsterfantasy.game.battle.*;





/** Clase donde transcurre la partida del juego. Una partida se distingue de otra mediante los datos (progreso) del heroe,
 * y el array de enemigos derrotados.
 * @author sanzx
 *
 */
public class Partida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(Partida.class.getName());


	/** Main con pruebas de carga y guardado de una partida
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Partida partida = new Partida("xabi", new Heroe()); //se crea una partida con los valores por defecto de inicio
		partida.partidanueva(); //se crea una partida nueva para anyadir al mapa de partidas
		partida.guardarpartida(); // se guarda la partida 
		
		System.out.println(Partidas.getMapapartidas().containsKey("xabi")); //se comprueba que se ha guardado correctamente la partida en el mapa
		
		
		
		Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado"); // se guarda el mapa de partidas en un fichero llamado "guardado"
		
	
		
		
		
		
	}
	
	
	/**
	 *  Nombre de la partida
	 */
	private String nombre;
	
	
	/**
	 *  Heroe de la partida
	 */
	private Heroe heroe;
	
	
	
	
	
	/** Getter del nombre de la partida
	 * @return nombre de la partida
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	/** Modifica nombre de la partida
	 * @param nombre  nombre a asignar a la partida
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	/** Getter del heroe de la partida
	 * @return heroe de la partida
	 */
	public Heroe getHeroe() {
		return heroe;
	}
	
	
	
	/** Modifica el heroe de la partida
	 * @param heroe  heroe de la partida a modificar
	 */
	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}
	
	
	
	/** Getter de la lista de enemigos disponibles y derrotados de la partida
	 * @return  lista de enemigos disponibles y derrotados de la partida
	 */

	
	
	/** Modifica la lista de enemigos disponibles y derrotados de la partida
	 * @param listaenemigos  lista de enemigos disponibles y derrotados de la partida a asignar
	 */

	
	
	/** Constructor de la partida
	 * @param nombre nombre de la partida
	 * @param heroe heroe de la partida 
	 * @param listaenemigos lista de enemigos disponibles y derrotados de la partida
	 */
	public Partida(String nombre, Heroe heroe) {
		
		this.heroe = heroe;

		this.nombre = nombre;
		
	}
	
	
	/** Se crea una partida nueva y se anyade al mapa contenedor de partidas
	 * 
	 */
	public void partidanueva() {
		Partidas.getMapapartidas().put(this.nombre, this);
		
	}
	
	
	/** Se carga una partida del mapa contenedor a traves del nombre de partida, y se asignan sus datos guardados a la partida actual
	 * @param nom
	 */
	public void cargarpartida(String nom) {
		
		if(Partidas.getMapapartidas().containsKey(nom)) {
		this.heroe = Partidas.getMapapartidas().get(nom).getHeroe();
		this.nombre = Partidas.getMapapartidas().get(nom).getNombre();
		
		logger.info("Existe la partida " + nom);}
		else {
			logger.severe("No existe la partida " + nom);
		}
		
		
	}
	
	/**  Guarda la partida en el mapa de partidas
	 * 
	 */
	public void guardarpartida() {
		if(Partidas.getMapapartidas().containsKey(this.nombre) == true) { //si existe una partida con ese nombre, se elimina la anterior y se actualiza
			Partidas.getMapapartidas().remove(this.nombre);
			logger.info("existe una partida con ese nombre, se elimina la anterior y se actualiza");
			
		}
		Partidas.getMapapartidas().put(this.nombre, this);
		logger.info("Partida nueva guardada");
		
	}
	
	

}

