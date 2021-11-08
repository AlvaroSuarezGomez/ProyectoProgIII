package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Controller {
	private static boolean right;
	private static boolean left;
	private static boolean up;
	private static boolean down;
	
	public static void Control(Avatar player) {
		right = Gdx.input.isKeyJustPressed(Keys.D);
		left = Gdx.input.isKeyJustPressed(Keys.A);
		down = Gdx.input.isKeyJustPressed(Keys.S);
		up = Gdx.input.isKeyJustPressed(Keys.W);
		
		if (right) {
			int cont = 0;
			while (cont < 64) {
				player.setX(player.getX()+1);
				cont++;
			}
		}
		else if (left) {
			int cont = 0;
			while (cont < 64) {
				player.setX((player.getX()-1));
				cont++;
		}
		}
		else if (up) {
			int cont = 0;
			while (cont < 64) {
				player.setY(player.getY()+1);
				cont++;
			}
		}
		else if (down) {
			int cont = 0;
			while (cont < 64) {
				player.setY(player.getY()-1);
				cont++;
			}
}
	}
}
