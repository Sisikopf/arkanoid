package com.vsu.csf.arkanoid.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Andrey on 19.12.2015.
 */
public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, platform, ball,
            undestructableBlock, lightBlock,
            mediumBlock1, mediumBlock2,
            heavyBlock1, heavyBlock2, heavyBlock3;

    public static void load() {

        texture = new Texture("textures.png");
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 18, 18);

        platform = new TextureRegion(texture, 18, 0, 30, 5);

        ball = new TextureRegion(texture, 48, 0, 30, 30);

        undestructableBlock = new TextureRegion(texture, 78, 0, 40, 20);

        lightBlock = new TextureRegion(texture, 118, 0, 40, 20);

        mediumBlock1 = new TextureRegion(texture, 158, 0, 40, 20);
        mediumBlock2 = new TextureRegion(texture, 158, 20, 40, 20);

        heavyBlock1 = new TextureRegion(texture, 198, 0, 40, 20);
        heavyBlock2 = new TextureRegion(texture, 198, 20, 40, 20);
        heavyBlock3 = new TextureRegion(texture, 198, 40, 40, 20);
    }

    public static void dispose() {
        texture.dispose();
    }
}
