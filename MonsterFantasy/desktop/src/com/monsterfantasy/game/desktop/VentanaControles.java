package com.monsterfantasy.game.desktop;

import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.monsterfantasy.game.gestionpartidas.Partidas;

public class VentanaControles extends JFrame{
	
	public static void main(String[] args) {
		VentanaControles v = new VentanaControles();
	}
	
	
	private JTable tabla = new JTable();
	private DefaultTableModel modelo;
	private JPanel panel;
	

	
	public VentanaControles() {
		
		this.setSize(300,190);
		setTitle( "Controles del juego" );
		
		Vector<String> cabeceras = new Vector<String>( Arrays.asList( "Boton", "Funcion") );
		modelo = new DefaultTableModel(  // Inicializa el modelo
			new Vector<Vector<String>>(),  
			cabeceras  // Cabeceras de la jtable
		);
		
		
		modelo.addRow( new String[] { "BOTON" , "FUNCION"} );
		modelo.addRow( new String[] { "W" , "Desplazar hacia arriba"} );
		modelo.addRow( new String[] { "A" , "Desplazar hacia la izquierda"} );
		modelo.addRow( new String[] { "S" , "Desplazar hacia abajo"} );
		modelo.addRow( new String[] { "D" , "Desplazar hacia la derecha"} );
		modelo.addRow( new String[] { "Z" , "Confirmar seleccion"} );
		modelo.addRow( new String[] { "X" , "Atras"} );
		modelo.addRow( new String[] { "C" , "Menu"} );
		
		tabla.setModel(modelo);
		tabla.getColumnModel().getColumn(0).setMinWidth(60);
		tabla.getColumnModel().getColumn(0).setMaxWidth(60);
		tabla.getColumnModel().getColumn(1).setMinWidth(200);
		tabla.getColumnModel().getColumn(1).setMaxWidth(200);	
		
		
	
		
		panel = new JPanel();
		panel.add(tabla);
		
		add(panel);
		
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}
}
