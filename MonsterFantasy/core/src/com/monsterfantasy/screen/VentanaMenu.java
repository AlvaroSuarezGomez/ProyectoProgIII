package com.monsterfantasy.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VentanaMenu extends JFrame {

	private JPanel panellabel = new JPanel();
	private JPanel botonera = new JPanel();
	private JButton nuevapartida = new JButton("Nueva Partida");
	private JButton cargarpartida = new JButton("Cargar Partida");
	private JLabel label = new JLabel("Bienvenido a Monster Fantasy");
	
	
	public VentanaMenu() {
		
		this.setTitle("Menu Principal");			
		this.setSize(620,160);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panellabel.add(label);
		botonera.add(nuevapartida);
		botonera.add(cargarpartida);
		
		this.add(panellabel, BorderLayout.CENTER);
		this.add(botonera, BorderLayout.SOUTH);
		
		
		
		
		
		nuevapartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
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