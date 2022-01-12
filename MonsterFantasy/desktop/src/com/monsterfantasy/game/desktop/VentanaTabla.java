package com.monsterfantasy.game.desktop;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.monsterfantasy.game.Monsterfantasy;
import com.monsterfantasy.game.battle.BaseDeDatos;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;


public class VentanaTabla extends JFrame {

	public static void main(String[] args) {
		
		VentanaTabla v = new VentanaTabla();
	}
	
	
	private DefaultTableModel mDatos;  
	private JTable tDatos;   
	private JButton botoncargar;
	private JButton botonborrar;
	private JPanel paneltabla;
	private JPanel panelbotones;
	private JButton atras;
	
	private static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());
	
	public VentanaTabla() {
		
		Partidas.setMapapartidas(Partidas.cargafichero("guardado"));
		
		setTitle( "Partidas del juego" );
		this.setSize(600,400);
		
		botoncargar = new JButton("Cargar Partida");
		botonborrar = new JButton("Eliminar Partida");
		atras = new JButton("Volver al Menu");
		
		paneltabla = new JPanel();
		panelbotones = new JPanel();
		
		panelbotones.add(botonborrar);
		panelbotones.add(botoncargar);
		panelbotones.add(atras);
		
		tDatos = new JTable();
		paneltabla.add(new JScrollPane(tDatos));
		
		this.add(panelbotones, BorderLayout.SOUTH);
		this.add(paneltabla, BorderLayout.CENTER);
		
		
		
		tDatos.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
		
		
		
		this.setLocation(400, 200);
		
		
		verPartidas();
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		botoncargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tDatos.getSelectedRow();
				String nombre = (String) tDatos.getValueAt(fila, 0);
				Partida partida = null;
				for (Partida p : Partidas.getMapapartidas().values()) {
					if (p.getNombre() == nombre) {
						partida = p;
					}
				}
				partida.cargarpartida(nombre);
				Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				config.resizable = false;
				config.width = 800;
				config.height = 600;
				config.foregroundFPS = 60;
				
				config.vSyncEnabled = true;
				
				config.x = 480;
				config.y = 150;
				
				Monsterfantasy game = new Monsterfantasy();
		
				game.setPartida(partida);
				game.setHeroe(partida.getHeroe());
			
				new LwjglApplication(game, config);
			}
		});
		
		
		botonborrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int fila = tDatos.getSelectedRow();
				String nombre = (String) tDatos.getValueAt(fila, 0);
				Partidas.getMapapartidas().remove(nombre);
				logger.info("Partida eliminada del jugador: " + nombre );
				
				Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
				verPartidas();
				
			}
		});
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu v = new VentanaMenu();
				dispose();
				
			}
		});
		
		this.setVisible(true);
	}
	
	
	
	private void verPartidas() {
		Vector<String> cabeceras = new Vector<String>( Arrays.asList( "Nombre", "Nivel") );
		mDatos = new DefaultTableModel(  // Inicializa el modelo
			new Vector<Vector<Object>>(),  
			cabeceras  // Cabeceras de la jtable
		);
		
		for (String nombre : Partidas.getMapapartidas().keySet() ) {  //Recorre el mapa de las partidas
			mDatos.addRow( new Object[] { nombre , Partidas.getMapapartidas().get(nombre).getHeroe().getNv() } );
		}
		tDatos.setModel( mDatos );
		// Pone tamaños a las columnas de la tabla
		tDatos.getColumnModel().getColumn(0).setMinWidth(240);
		tDatos.getColumnModel().getColumn(0).setMaxWidth(240);
		tDatos.getColumnModel().getColumn(1).setMinWidth(240);
		tDatos.getColumnModel().getColumn(1).setMaxWidth(240);	
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tDatos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tDatos.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	}

}
