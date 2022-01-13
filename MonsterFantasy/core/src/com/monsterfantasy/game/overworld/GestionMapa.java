package com.monsterfantasy.game.overworld;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;

public class GestionMapa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4220590241585129583L;
	
	private static Logger logger = Logger.getLogger(GestionMapa.class.getName());
	
	private static Celda[][] celdasSave;
	
	public static void guardarfichero(Celda[][] celdas, String nombrefic) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombrefic));
			oos.writeObject(celdas);
			oos.close();
			logger.info("Se ha guardado el fichero del Overworld");
		} catch (IOException e) {
			logger.severe("Error de guardado del Overworld");
			System.out.println(e);
		}
	}
	
	public static Celda[][] cargafichero(String nombrefic) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombrefic));
			Celda[][] celdas = (Celda[][]) ois.readObject();
			ois.close();
			logger.info("Fichero cargado");
			return celdas;
		} catch (IOException | ClassNotFoundException e) {
			logger.severe("Error de cargado, se crea un nuevo Overworld");

			// si no hay fichero, se crea un mapa vacios
			Overworld overworld = new Overworld();
			overworld.crearCeldas();
			return overworld.getCeldas();
		}
	}

	public static Celda[][] getCeldasSave() {
		return celdasSave;
	}

	public static void setCeldasSave(Celda[][] celdasSave) {
		GestionMapa.celdasSave = celdasSave;
	}

}
