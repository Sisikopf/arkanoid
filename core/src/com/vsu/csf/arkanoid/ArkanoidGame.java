package com.vsu.csf.arkanoid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vsu.csf.arkanoid.gamehelpers.AssetLoader;
import com.vsu.csf.arkanoid.screens.GameScreen;

public class ArkanoidGame extends Game {
	private final static String TAG = "ArkanoidGame";

	@Override
	public void create () {
		Gdx.app.log(TAG, "created");
        AssetLoader.load();
		setScreen(new GameScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
