package com.monsterfantasy.game.gestionpartidas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

import com.monsterfantasy.game.battle.BaseDeDatos;

/**  Gestor de las partidas
 * @author sanzx
 *
 */
public class Partidas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(Partidas.class.getName());
	/**
	 * Mapa de todas las partidas del juego, siendo el nombre de partida la clave y
	 * la partida en si el cotenido
	 */
	private static HashMap<String, Partida> mapapartidas = new HashMap<String, Partida>();

	/**
	 * getter del mapa de las partidas del juego, siendo el nombre de partida la
	 * clave y la partida en si el cotenido
	 * 
	 * @return
	 */
	public static HashMap<String, Partida> getMapapartidas() {
		return mapapartidas;
	}

	/**
	 * Modifica el mapa de las partidas del juego, siendo el nombre de partida la
	 * clave y la partida en si el cotenido
	 * 
	 * @param mapapartidas mapa de las partidas de juego a modificar
	 */
	public static void setMapapartidas(HashMap<String, Partida> mapapartidas) {
		Partidas.mapapartidas = mapapartidas;
	}

	/**
	 * Guarda en un fichero binario externo el mapa de las partidas
	 * 
	 * @param mapa      mapa a guardar
	 * @param nombrefic nombre del fichero
	 */
	public static void guardarfichero(HashMap<String, Partida> mapa, String nombrefic) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombrefic));
			oos.writeObject(mapa);
			oos.close();
			logger.info("Se ha guardado el fichero");
		} catch (IOException e) {
			logger.severe("Error de guardado");
		}
	}

	/**
	 * Carga un mapa de partidas a traves de un fichero binario externo
	 * 
	 * @param nombrefic nombre del fichero del cual se carga el mapa de partidas
	 * @return el mapa de partidas del fichero
	 */
	public static HashMap<String, Partida> cargafichero(String nombrefic) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombrefic));
			HashMap<String, Partida> mapa = (HashMap<String, Partida>) ois.readObject();
			ois.close();
			logger.info("Fichero cargado");
			return mapa;
		} catch (IOException | ClassNotFoundException e) {
			logger.severe("Error de cargado, se crea un mapa nuevo de partidas");

			// si no hay fichero, se crea un mapa vacio
			HashMap<String, Partida> mapa = new HashMap<String, Partida>();
			return mapa;
		}
	}

}
