package com.monsterfantasy.game.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Controller {

	public static void Control(Avatar player) {
		if (Gdx.input.isKeyPressed(Keys.D)) {
			int cont = 0;
			while (cont < 16) {
			player.setX(player.getX()+1);
			cont++;
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.A)) {
			int cont = 0;
			while (cont < 16) {
			player.setX(player.getX()-1);
			cont++;
		}
		}
		else if (Gdx.input.isKeyPressed(Keys.W)) {
			int cont = 0;
			while (cont < 16) {
			player.setY(player.getY()+1);
			cont++;
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.S)) {
			int cont = 0;
			while (cont < 16) {
			player.setY(player.getY()-1);
			cont++;
			}
}
	}
	
}
