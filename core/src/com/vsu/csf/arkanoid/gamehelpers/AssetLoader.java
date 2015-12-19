package com.vsu.csf.arkanoid.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Andrey on 19.12.2015.
 */
public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, platform, ball;

    public static void load() {

        texture = new Texture("textures.png");
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 18, 18);
        bg.flip(false, false);

        platform = new TextureRegion(texture, 18, 0, 30, 5);
        platform.flip(false, false);

        ball = new TextureRegion(texture, 48, 0, 30, 30);
        ball.flip(false, false);
    }

    public static void dispose() {
        texture.dispose();
    }
}
