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
	private static int cont = 0;
	private static int contlength = 64;
	private static int x_offset = 0;
	private static int y_offset = 0;
	private static boolean lockedMovement = false;
	public static Avatar player;
	public static Overworld overworld;
	private static OverworldScene overworldScene;
	
	private static float elapsed = 0;
	private final static float waitBetweenFrames = 0.5f;
	private static int frameIndex = 0;
	private static TextureRegion[] frames = new TextureRegion[4];
	
	public static void animacionPersonaje(LookDirection direccion, boolean animar) {
		
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
	}
	
	public static void controlSinThread() {
		if (Gdx.input.isKeyJustPressed(Keys.D))  {
			if ((overworld.getCeldas()[(int) ((player.getX()/64)+1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Hierba) && (!isLockedMovement())) {
				overworldScene.setTriggerBattleRandomizer(true);
			} else if ((overworld.getCeldas()[(int) ((player.getX()/64)+1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Tienda) && (!isLockedMovement())) {
				overworldScene.setTriggerShop(true);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			if ((overworld.getCeldas()[(int) ((player.getX()/64)-1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Hierba) && (!isLockedMovement())) {
				overworldScene.setTriggerBattleRandomizer(true);
			} else if ((overworld.getCeldas()[(int) ((player.getX()/64)-1)][(int) (player.getY()/64)].getTipo() == TipoCelda.Tienda) && (!isLockedMovement())) {
				overworldScene.setTriggerShop(true);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)-1)].getTipo() == TipoCelda.Hierba) && (!isLockedMovement())) {
				overworldScene.setTriggerBattleRandomizer(true);
		}	else if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)-1)].getTipo() == TipoCelda.Tienda) && (!isLockedMovement())) {
			overworldScene.setTriggerShop(true);
		}
		}
		if (Gdx.input.isKeyJustPressed(Keys.W)) {
			if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)+1)].getTipo() == TipoCelda.Hierba) && (!isLockedMovement())) {
				overworldScene.setTriggerBattleRandomizer(true);
		}	else if ((overworld.getCeldas()[(int) ((player.getX()/64))][(int) ((player.getY()/64)+1)].getTipo() == TipoCelda.Tienda) && (!isLockedMovement())) {
			overworldScene.setTriggerShop(true);
		} 
		}
	}
	
	public static void control(final boolean move) {
		Thread mov = new Thread() {
			@Override
			public void run() {
				if (!isLockedMovement()) {
				if (overworldScene.getDirection() == LookDirection.Right) {	 x_offset = 1; y_offset = 0; }
				if (overworldScene.getDirection() == LookDirection.Left) {	x_offset = -1; y_offset = 0;}
				if (overworldScene.getDirection() == LookDirection.Down) {	x_offset = 0; y_offset = -1;}
				if (overworldScene.getDirection() == LookDirection.Up) { x_offset = 0; y_offset = 1;}
				} 
					if ((move) && (overworld.getCeldas()[(int) ((player.getX()/64)+x_offset)][(int) (player.getY()/64)+y_offset].getTipo() != TipoCelda.Arbol)) {		
						setLockedMovement(true);
						while (cont < contlength) {
						player.setX(player.getX() + x_offset);
						player.setY(player.getY() + y_offset);
						animacionPersonaje(overworldScene.getDirection(), true);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cont++; 
						animacionPersonaje(overworldScene.getDirection(), false);
					} 
					if (cont >= contlength) {
					cont = 0;
				}
					setLockedMovement(false);
					Thread.currentThread().interrupt();
			}
			}};
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

	public static boolean isLockedMovement() {
		return lockedMovement;
	}

	public static void setLockedMovement(boolean lockedMovement) {
		Controller.lockedMovement = lockedMovement;
	}

}
