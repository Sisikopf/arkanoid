package com.vsu.csf.arkanoid.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.vsu.csf.arkanoid.gameobjects.Platform;

/**
 * Created by dimko_000 on 16.12.2015.
 */
public class GameWorld {
    private final static String TAG = "GameWorld";

    public final static float GAME_HEIGHT = 300;
    public final static float GAME_WIDTH = 300;

    public final static float PLATFORM_HEIGHT = 20;
    public final static float INIT_PLATFORM_WIDTH = 60;
    public final static float INIT_PLATFORM_SPEED = 50;

    private Platform platform;

    public GameWorld() {
        platform = new Platform(GAME_WIDTH / 2, GAME_HEIGHT,
                INIT_PLATFORM_SPEED, INIT_PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    public void update(float delta) {
        Gdx.app.log(TAG, "update");
        platform.update(delta);
    }

    public Platform getPlatform(){
        return platform;
    }
}
