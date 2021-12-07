package com.monsterfantasy.screen;

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

import com.monsterfantasy.game.battle.BaseDeDatos;
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
	
	private static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());
	
	public VentanaTabla() {
		
		Partidas.setMapapartidas(Partidas.cargafichero("guardado"));
		
		setTitle( "Partidas del juego" );
		this.setSize(600,400);
		
		botoncargar = new JButton("Cargar Partida");
		botonborrar = new JButton("Eliminar Partida");
		
		paneltabla = new JPanel();
		panelbotones = new JPanel();
		
		panelbotones.add(botonborrar);
		panelbotones.add(botoncargar);
		
		tDatos = new JTable();
		paneltabla.add(new JScrollPane(tDatos));
		
		this.add(panelbotones, BorderLayout.SOUTH);
		this.add(paneltabla, BorderLayout.CENTER);
		
		
		
		tDatos.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
		
		
		
		
		
		
		verPartidas();
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		
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
