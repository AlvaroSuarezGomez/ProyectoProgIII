package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	
	private static float elapsed = 0;
	private final static float waitBetweenFrames = 0.5f;
	private static int frameIndex = 0;
	
	public static void animacionPersonaje(LookDirection direccion, boolean animar) {
		TextureRegion[] frames = new TextureRegion[4];
		if (direccion == LookDirection.Up) {
			if (animar) {
			frames[0] = new TextureRegion(player.getP_texture(), 0, 192, 64, 64);
			frames[1] = new TextureRegion(player.getP_texture(), 64, 192, 64, 64);
			frames[2] = new TextureRegion(player.getP_texture(), 128, 192, 64, 64);
			frames[3] = new TextureRegion(player.getP_texture(), 192, 192, 64, 64);
			} else {
				frames[0] = new TextureRegion(player.getP_texture(), 0, 192, 64, 64);
				frames[1] = new TextureRegion(player.getP_texture(), 0, 192, 64, 64);
				frames[2] = new TextureRegion(player.getP_texture(), 0, 192, 64, 64);
				frames[3] = new TextureRegion(player.getP_texture(), 0, 192, 64, 64);
			}
		} else if (direccion == LookDirection.Down) {
			if (animar) {
			frames[0] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
			frames[1] = new TextureRegion(player.getP_texture(), 64, 0, 64, 64);
			frames[2] = new TextureRegion(player.getP_texture(), 128, 0, 64, 64);
			frames[3] = new TextureRegion(player.getP_texture(), 192, 0, 64, 64);
			} else {
				frames[0] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
				frames[1] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
				frames[2] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
				frames[3] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
			}
		} else if (direccion == LookDirection.Right) {
			if (animar) {
			frames[0] = new TextureRegion(player.getP_texture(), 0, 128, 64, 64);
			frames[1] = new TextureRegion(player.getP_texture(), 64, 128, 64, 64);
			frames[2] = new TextureRegion(player.getP_texture(), 128, 128, 64, 64);
			frames[3] = new TextureRegion(player.getP_texture(), 192, 128, 64, 64);
			} else {
				frames[0] = new TextureRegion(player.getP_texture(), 0, 128, 64, 64);
				frames[1] = new TextureRegion(player.getP_texture(), 0, 128, 64, 64);
				frames[2] = new TextureRegion(player.getP_texture(), 0, 128, 64, 64);
				frames[3] = new TextureRegion(player.getP_texture(), 0, 128, 64, 64);
			}
		} else if ((direccion == LookDirection.Left)) {
			if (animar) {
			frames[0] = new TextureRegion(player.getP_texture(), 0, 64, 64, 64);
			frames[1] = new TextureRegion(player.getP_texture(), 64, 64, 64, 64);
			frames[2] = new TextureRegion(player.getP_texture(), 128, 64, 64, 64);
			frames[3] = new TextureRegion(player.getP_texture(), 192, 64, 64, 64);
			} else {
				frames[0] = new TextureRegion(player.getP_texture(), 0, 64, 64, 64);
				frames[1] = new TextureRegion(player.getP_texture(), 0, 64, 64, 64);
				frames[2] = new TextureRegion(player.getP_texture(), 0, 64, 64, 64);
				frames[3] = new TextureRegion(player.getP_texture(), 0, 64, 64, 64);
			}
		} else {
			frames[0] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
			frames[1] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
			frames[2] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
			frames[3] = new TextureRegion(player.getP_texture(), 0, 0, 64, 64);
		}
		
		elapsed += Gdx.graphics.getDeltaTime();
			if (elapsed >= waitBetweenFrames) {
				if (frameIndex < 3) {
					frameIndex += 1;
					elapsed = 0;
				} else {
					frameIndex = 0;
					elapsed = 0;
				}
			}
		player.setP_texture_region(frames[frameIndex]);
		System.out.println(direccion + ", " + animar);
	}
	
	public static void controlSinThread() {
		if (Gdx.input.isKeyPressed(Keys.D))  {
			if ((overworld.getCeldas()[(int) ((player.getX()/64)+1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
			} else if ((overworld.getCeldas()[(int) ((player.getX()/64)+1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Tienda) && (!lockedMovement)) {
				overworldScene.cargarTienda();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			if ((overworld.getCeldas()[(int) ((player.getX()/64)-1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
			} else if ((overworld.getCeldas()[(int) ((player.getX()/64)-1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Tienda) && (!lockedMovement)) {
				overworldScene.cargarTienda();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)-1)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
			overworldScene.battleRandomizer();
		}	else if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)-1)].getTipo() == TipoCelda.Tienda) && (!lockedMovement)) {
			overworldScene.cargarTienda();
		}
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)+1)].getTipo() == TipoCelda.Hierba) && (!lockedMovement)) {
				overworldScene.battleRandomizer();
		}	else if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)+1)].getTipo() == TipoCelda.Tienda) && (!lockedMovement)) {
			overworldScene.cargarTienda();
		}
		}
	}
	
	public static void control() {
		Thread mov = new Thread() {
			@Override
			public void run() {
				if (!lockedMovement) {
				if (Gdx.input.isKeyJustPressed(Keys.D)) {	right = true; animacionPersonaje(LookDirection.Right, true); }
				if (Gdx.input.isKeyJustPressed(Keys.A)) {	left = true; animacionPersonaje(LookDirection.Left, true); }
				if (Gdx.input.isKeyJustPressed(Keys.S)) {	down = true; animacionPersonaje(LookDirection.Down, true); }
				if (Gdx.input.isKeyJustPressed(Keys.W)) {	up = true; animacionPersonaje(LookDirection.Up, true); }
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
	}

	public static OverworldScene getOverworldScene() {
		return overworldScene;
	}

	public static void setOverworldScene(OverworldScene overworldScene) {
		Controller.overworldScene = overworldScene;
	}

}
