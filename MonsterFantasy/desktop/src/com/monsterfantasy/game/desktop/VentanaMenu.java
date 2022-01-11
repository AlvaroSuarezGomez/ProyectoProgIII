package com.monsterfantasy.game.desktop;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.monsterfantasy.game.Monsterfantasy;
import com.monsterfantasy.game.OverworldScene;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;



public class VentanaMenu extends JFrame {

	private JPanel panellabel = new JPanel();
	private JPanel botonera = new JPanel();
	private JButton nuevapartida = new JButton("Nueva Partida");
	private JButton cargarpartida = new JButton("Cargar Partida");
	private JButton controles = new JButton("Controles del juego");
	private JLabel label = new JLabel();
	private static VentanaMenu ventana;
	
	public VentanaMenu() {
		
		this.setTitle("Menu Principal");			
		this.setSize(960,720);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Image img;
        try {
            img = ImageIO.read(getClass().getResource("MonsterFantasy.jpg"));
            label.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		panellabel.add(label,BorderLayout.CENTER);
		botonera.add(nuevapartida);
		botonera.add(cargarpartida);
		botonera.add(controles);
		
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
				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				config.resizable = false;
				config.width = 800;
				config.height = 600;
				config.foregroundFPS = 60;
				
				
				
				config.vSyncEnabled = true;
				
				config.x = 480;
				config.y = 150;
				
				Monsterfantasy game = new Monsterfantasy();
		
				game.setPartida(p);
				game.setHeroe(p.getHeroe());
			
				new LwjglApplication(game, config);
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