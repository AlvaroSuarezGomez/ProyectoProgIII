package com.monsterfantasy.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;



public class VentanaMenu extends JFrame {

	private JPanel panellabel = new JPanel();
	private JPanel botonera = new JPanel();
	private JButton nuevapartida = new JButton("Nueva Partida");
	private JButton cargarpartida = new JButton("Cargar Partida");
	private JLabel label = new JLabel("Bienvenido a Monster Fantasy");
	private static VentanaMenu ventana;
	
	
	public VentanaMenu() {
		
		this.setTitle("Menu Principal");			
		this.setSize(620,160);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panellabel.add(label);
		botonera.add(nuevapartida);
		botonera.add(cargarpartida);
		
		this.add(panellabel, BorderLayout.CENTER);
		this.add(botonera, BorderLayout.SOUTH);
		
		
		Partidas.setMapapartidas(Partidas.cargafichero("guardado")); 
		
		
		nuevapartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String resp = JOptionPane.showInputDialog( ventana, "Nombre del jugador:", "" );
				Partida p = new Partida(resp, new Heroe());
				p.partidanueva();
				Partidas.guardarfichero(Partidas.getMapapartidas(), "guardado");
				
			}
		});
		
		cargarpartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaTabla v = new VentanaTabla();
				dispose();
			}
		});
		
		
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		VentanaMenu menu = new VentanaMenu();
	
		
		
	}
}