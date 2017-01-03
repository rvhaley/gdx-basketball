package com.tryla_g.basketball.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tryla_g.basketball.game.GdxBasketballMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gdx-Basketball";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new GdxBasketballMain(), config);
	}
}
