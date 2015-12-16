package com.vsu.csf.arkanoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vsu.csf.arkanoid.ArkanoidGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Arkanoid";
		config.width = 600;
		config.height = 600;
		new LwjglApplication(new ArkanoidGame(), config);
	}
}
