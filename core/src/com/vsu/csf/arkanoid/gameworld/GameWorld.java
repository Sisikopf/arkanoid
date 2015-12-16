package com.vsu.csf.arkanoid.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;

/**
 * Created by dimko_000 on 16.12.2015.
 */
public class GameWorld {
    private final static String TAG = "GameWorld";
    private Rectangle rect = new Rectangle(10, 10, 17, 12);

    public void update(float delta) {
        Gdx.app.log(TAG, "update");
        rect.x++;
        if (rect.x > 300) {
            rect.x = 0;
        }
    }

    public Rectangle getRect() {
        return rect;
    }
}
