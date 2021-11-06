package com.monsterfantasy.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.monsterfantasy.game.Monsterfantasy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = 800;
		config.height = 600;
		
		config.vSyncEnabled = true;
		
		config.x = 480;
		config.y = 150;
		
		new LwjglApplication(new Monsterfantasy(), config);
	}
}
