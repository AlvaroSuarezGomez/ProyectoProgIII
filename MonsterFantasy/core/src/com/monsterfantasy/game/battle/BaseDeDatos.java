package com.monsterfantasy.game.battle;

import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jdi.connect.spi.Connection;

public class BaseDeDatos {
	private static Connection conexion;
	
	public static boolean abrirConexion(String nombreBD,boolean reiniciaBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			if(reiniciaBD) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXIST tablaPrincipalDelProyecto";
				logger.log(Level.INFO,"Statement: "+ sent);
				statement.executeUpdate(sent);
				sent="CREATE TABLE tablaPrincipalDelProyecto (Atributos)";
				logger.log(Level.INFO, "Statement: "+ sent);
				
			}
			
		}catch(Exception e){
			
		}
	}
}
