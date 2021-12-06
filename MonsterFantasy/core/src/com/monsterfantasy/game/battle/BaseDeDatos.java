package com.monsterfantasy.game.battle;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import com.sun.jdi.connect.spi.Connection;
public class BaseDeDatos {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());
	
	public static boolean abrirConexion(String nombreBD,boolean reiniciaBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite" + nombreBD);
			if(reiniciaBD) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXIST enemigos";
				logger.log(Level.INFO,"Statement: "+ sent);
				statement.executeUpdate(sent);
				sent="CREATE TABLE enemigos (pv int, pvmax int, ataque int, defensa int, posicionguardia boolean default false, exprecompensa int, nombre varchar(100) PRIMARY KEY AUTOINCREMENT, espiritu int) ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				sent="CREATE TABLE ataques(nombres String INTEGER PRIMARY KEY AUTOINCREMENT, espiritus int, potencia int) ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				sent="CREATE TABLE equipacion(nombres String INTEGER PRIMARY KEY AUTOINCREMENT, puntosDeDefensa int, precio int) ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				sent="CREATE TABLE pociones(nombres String INTEGER PRIMARY KEY AUTOINCREMENT, precio int, puntosSalud int) ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				try {
					Scanner scanner = new Scanner( BaseDatos.class.getResourceAsStream("enemigos.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into enemigo (pv, pvmax, defensa, posicionguardia,exorecompensa,nombre,espiritu) values (" + datos[0] + ",'" + datos[1] + "'," + datos[2] + ","+datos[3] + "," + datos[4] +","+ datos[5] +","+ datos[6]+" );";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
						
					}
					scanner.close();
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("ataques.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into ataques (nombre, potencia, espiritus) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("equipaciones.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into quipacion (nombre,puntosDeDefensa,precio) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("pociones.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into equipacion (nombre,precio,puntosSalud) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					
				}catch(Exception e) {
					logger.log(Level.SEVERE,"Exception",e);
				}
				
			}
			
		}catch(Exception e){
			logger.log(Level.SEVERE,"Exception",e);
			return false;
		}
	}
	
	
}
