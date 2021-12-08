package com.monsterfantasy.game.battle;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class BaseDeDatos {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());
	
	public static void abrirConexion(String nombreBD,boolean reiniciaBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			if(reiniciaBD) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXIST enemigos;";
				statement.executeUpdate(sent);
				sent="CREATE TABLE enemigos (pv int, pvmax int, ataque int, defensa int, posicionguardia boolean, exprecompensa int, nombre varchar(100) PRIMARY KEY, espiritu int);";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				sent = "DROP TABLE IF EXIST ataques;";
				statement.executeUpdate(sent);
				sent="CREATE TABLE ataques(nombres String INTEGER PRIMARY KEY , espiritus int, potencia int); ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				sent = "DROP TABLE IF EXIST equipacion;";
				statement.executeUpdate(sent);
				sent="CREATE TABLE equipacion(nombres String INTEGER PRIMARY KEY , puntosDeDefensa int, precio int); ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				sent = "DROP TABLE IF EXIST pociones;";
				statement.executeUpdate(sent);
				sent="CREATE TABLE pociones(nombres String INTEGER PRIMARY KEY , precio int, puntosSalud int); ";
				logger.log(Level.INFO, "Statement: "+ sent);
				statement.executeUpdate(sent);
				try {
					Scanner scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("enemigos.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into enemigo (pv, pvmax, defensa, posicionguardia,exorecompensa,nombre,espiritu) values (" + datos[0] + ",'" + datos[1] + "'," + datos[2] + ","+datos[3] + "," + datos[4] +","+ datos[5] +","+ datos[6]+" );";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
						
					}
					scanner.close();
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("ataques.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into ataques (nombre, potencia, espiritus) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("equipaciones.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into quipacion (nombre,puntosDeDefensa,precio) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("pociones.txt") );
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
		}
	}
	
	public static ArrayList<Enemigo> getEnemigos() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
			String sent = "select * from enemigos;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) {
				int pv = rs.getInt("pv");
				int pvmax = rs.getInt("pvmax");
				int ataque = rs.getInt("ataque");
				int defensa = rs.getInt("defensa");
				boolean posicionguardia = rs.getBoolean("posicionguardia");
				int exprecompensa = rs.getInt("exprecompensa");
				String nombre = rs.getString("nombre");
				int espiritu = rs.getInt("espiritu");
				listaEnemigos.add( new Enemigo ( pv, pvmax, ataque, defensa,posicionguardia,exprecompensa,nombre,espiritu) );
			}
			return listaEnemigos;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	public static ArrayList<AtaqueEspecial> getAtaques() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<AtaqueEspecial> listaAtaques = new ArrayList<>();
			String sent = "select * from ataques;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				String nombre = rs.getString("nombre");
				int potencia = rs.getInt("potencia");
				int espiritu = rs.getInt("espiritu");
				listaAtaques.add( new AtaqueEspecial(nombre,potencia,espiritu) );
			}
			return listaAtaques;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	public static ArrayList<Equipacion> getEquipaciones() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Equipacion> listaEquipacion = new ArrayList<>();
			String sent = "select * from equipacion;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				int puntosdefensa = rs.getInt("puntosDeDefensas");
				String nombre = rs.getString("nombre");
				int precio = rs.getInt("precio");
				listaEquipacion.add( new Equipacion(puntosdefensa,nombre,precio) );
			}
			return listaEquipacion;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	public static ArrayList<Pociones> getPociones() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Pociones> listaPociones = new ArrayList<>();
			String sent = "select * from pociones;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				String nombre = rs.getString("nombre");
				int precio = rs.getInt("precio");
				int puntossalud = rs.getInt("puntossalud");
				listaPociones.add( new Pociones(nombre,precio,puntossalud) );
			}
			return listaPociones;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		abrirConexion("BaseDatos", true);
	}
	
	
}
