package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	public static Avatar player;
	
	public static void Control() {
		Thread mov = new Thread() {
			@Override
			public void run() {
				if (Gdx.input.isKeyJustPressed(Keys.D)) right = true;
				if (Gdx.input.isKeyJustPressed(Keys.A)) left = true;
				if (Gdx.input.isKeyJustPressed(Keys.S)) down = true;
				if (Gdx.input.isKeyJustPressed(Keys.W)) up = true;
					if (right) {
						right = false;
						while (cont < contlength) {
						player.setX(player.getX() + offset);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(player.getX());
						cont++;
					} if (Gdx.input.isKeyPressed(Keys.D)) {
						right = true;
					}
					}
					if (left) {
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
					} if (Gdx.input.isKeyPressed(Keys.D)) {
						right = true;
					}
					}
					if (up) {
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
					} if (Gdx.input.isKeyPressed(Keys.D)) {
						up = true;
					}
					}
					if (down) {
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
					}
				} if (cont >= contlength) {
					cont = 0;
				}
			}
		};
		mov.start();
	}
	
	public static void SetTexture(Avatar player) {
		player.setP_texture(new Texture("Nate.png"));
		player.setP_texture_region(new TextureRegion(player.getP_texture(), 0, 0, 64, 64));
		
	}
}
