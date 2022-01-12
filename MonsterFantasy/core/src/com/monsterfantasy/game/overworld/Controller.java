package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.monsterfantasy.game.BattleScene;
import com.monsterfantasy.game.Monsterfantasy;
import com.monsterfantasy.game.OverworldScene;

public class Controller {
	private static boolean right;
	private static boolean left;
	private static boolean up;
	private static boolean down;
	private static float newX;
	private static float newY;
	private static int cont = 0;
	private static int contlength = 64;
	private static int offset = 1;
	private static boolean lockedMovement = false;
	public static Avatar player;
	public static Overworld overworld;
	private static OverworldScene overworldScene;
	
	public static void controlSinThread() {
		if ((Gdx.input.isKeyJustPressed(Keys.D)) && (overworld.getCeldas()[(int) ((player.getX()/64)+1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
		}
		if ((Gdx.input.isKeyJustPressed(Keys.A)) && (overworld.getCeldas()[(int) ((player.getX()/64)-1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
		}
		if ((Gdx.input.isKeyJustPressed(Keys.S)) && (overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)+1)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
		}
		if ((Gdx.input.isKeyJustPressed(Keys.W)) && (overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)-1)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
		}
	}
	
	public static void control() {
		Thread mov = new Thread() {
			@Override
			public void run() {
				if (!lockedMovement) {
				if (Gdx.input.isKeyJustPressed(Keys.D)) right = true;
				if (Gdx.input.isKeyJustPressed(Keys.A)) left = true;
				if (Gdx.input.isKeyJustPressed(Keys.S)) down = true;
				if (Gdx.input.isKeyJustPressed(Keys.W)) up = true;
				}
					if ((right) && (overworld.getCeldas()[(int) ((player.getX()/64)+1)][(int) (player.getY()/64)].getTipo() != TipoCelda.Arbol)) {
						lockedMovement = true;
						right = false;
						while (cont < contlength) {
						player.setX(player.getX() + offset);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cont++;
					} if (Gdx.input.isKeyPressed(Keys.D)) {
						right = true;
						control();
					} lockedMovement = false;
					} else { 
						right = false;
					}
					
					if ((left) && (overworld.getCeldas()[(int) ((player.getX()/64)-1)][(int) (player.getY()/64)].getTipo() != TipoCelda.Arbol)) {
						lockedMovement = true;
						left = false;
						while (cont < contlength) {
						player.setX(player.getX() - offset);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cont++;
					}lockedMovement = false; 
					if (Gdx.input.isKeyPressed(Keys.A)) {
						left = true;
						control();
					}
					} else {
						left = false;
					}
					
					if ((up && (overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)+1)].getTipo() != TipoCelda.Arbol))) {
						lockedMovement = true;
						up = false;
						while (cont < contlength) {
						player.setY(player.getY() + offset);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cont++;
					}	lockedMovement = false;
						if (Gdx.input.isKeyPressed(Keys.W)) {
						up = true;
						control();
					}
					} else {
						up = false;
					}
					
					if (down && (overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)-1)].getTipo() != TipoCelda.Arbol)) {
						lockedMovement = true;
						down = false;
						while (cont < contlength) {
						player.setY(player.getY() - offset);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cont++;
					} lockedMovement = false;
						if (Gdx.input.isKeyPressed(Keys.S)) {
						down = true;
						control();
						}
				} else {
					down = false; 
				}
					if (cont >= contlength) {
					cont = 0;
				}
			}
		};
		mov.start();
	}
	
	public static void setTexture(Avatar player) {
		player.setP_texture(new Texture("Nate.png"));
		player.setP_texture_region(new TextureRegion(player.getP_texture(), 0, 0, 64, 64));
		
	}

	public static OverworldScene getOverworldScene() {
		return overworldScene;
	}

	public static void setOverworldScene(OverworldScene overworldScene) {
		Controller.overworldScene = overworldScene;
	}
	
}
