package ventanas;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class Ventana_Menu extends JFrame {

	public static final int altura=600;											///Dimensiones de la pantalla
	public static final int anchura=400;
	public static final int c=600;
	public static final int d=300;
	
	public void VentanaMenu() {	
		this.setTitle("Menu");													///Titulo de la pantalla
		this.setMinimumSize(new Dimension(altura, anchura));
		this.setLocation(c,d);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Container cp=this.getContentPane();										///Creamos un panel
		cp.setLayout(new GridLayout(5,3));										///Dividimos el panel en cachos
		JButton CrearPartida = new JButton("Crear partida");					///Creo el boton crear partida
		JButton CargarPartida = new JButton("Cargar partida");					///Creo el boton Cargar partida
		for (int i=0;i<15;i++ ) {
			if(i==4) {
				cp.add(CrearPartida);											///Pongo el boton de crear partida arriba
				
			}else if(i==10){
				cp.add(CargarPartida);											///Pongo el boton de cargar partida abajo
				
			}else {
				cp.add(new JLabel(" "));										///Donde no haya botones pongo hueco vacio
			}
		}
		
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
		new Ventana_Menu();
		
	}
}