package ventanas;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class VentanaMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2395888841778608798L;
	
	public static final int altura=600;											///Dimensiones de la pantalla
	public static final int anchura=800;
	public static final int c=600;
	public static final int d=300;
	
	public VentanaMenu() {	
		this.setTitle("Menu");													///Titulo de la pantalla
		this.setMinimumSize(new Dimension(altura, anchura));
		this.setSize(new Dimension(altura, anchura));
		this.setLocation(c,d);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		
		Container cp = this.getContentPane();										///Creamos un panel
		cp.setLayout(new GridLayout(5,3));										///Dividimos el panel en cachos
		JButton CrearPartida = new JButton("Crear partida");					///Creo el boton crear partida
		JButton CargarPartida = new JButton("Cargar partida");					///Creo el boton Cargar partida
		for (int i=0; i<=3; i++) {
			if (i==0) {
				cp.add(CrearPartida);											///Pongo el boton de crear partida arriba
			} else if (i==2) {
				cp.add(CargarPartida);											///Pongo el boton de cargar partida abajo
			} else {
				cp.add(new JLabel(""));
			}
				
		}	
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
		VentanaMenu menu = new VentanaMenu();
		menu.setVisible(true);
		menu.setSize(menu.anchura, menu.altura);
		
	}
}