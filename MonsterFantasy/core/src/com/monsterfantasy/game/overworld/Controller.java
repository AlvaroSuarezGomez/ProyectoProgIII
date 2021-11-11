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
	private static int cont;
	private static int contlength;
	private static int offset;
	public static Avatar player;
	
	@SuppressWarnings("unused")
	public static void Control() {
		abstract class MovTime implements Runnable {
			MovTime movimiento = new MovTime() {

			@Override
			public void run() {
				right = Gdx.input.isKeyJustPressed(Keys.D);
				left = Gdx.input.isKeyJustPressed(Keys.A);
				down = Gdx.input.isKeyJustPressed(Keys.S);
				up = Gdx.input.isKeyJustPressed(Keys.W);
				while (cont <= contlength) {
					if (right) {
						player.setX(player.getX() + offset);
						cont++;
						System.out.println("A");
			}
			}
		}};
			}
		}
	

	
	
	public static void SetTexture(Avatar player) {
		player.setP_texture(new Texture("Nate.png"));
		player.setP_texture_region(new TextureRegion(player.getP_texture(), 0, 0, 64, 64));
		
	}
}
