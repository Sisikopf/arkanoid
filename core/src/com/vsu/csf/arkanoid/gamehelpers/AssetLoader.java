package com.vsu.csf.arkanoid.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Andrey on 19.12.2015.
 */
public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, platform, ball, bonus, life,
            undestructableBlock, lightBlock,
            mediumBlock1, mediumBlock2,
            heavyBlock1, heavyBlock2, heavyBlock3;

    public static BitmapFont font, shadow;
    public static Sound collisionSound;
    public static Sound bonusSound;
    public static Sound gamewinSound;
    public static Sound gameoverSound;
    public static void load() {

        texture = new Texture("textures.png");
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 600, 600);
        bg.flip(false, true);

        platform = new TextureRegion(texture, 890, 0, 120, 20);
        platform.flip(false, true);

        bonus = new TextureRegion(texture, 1010, 0, 100, 100);
        bonus.flip(false, true);

        life = new TextureRegion(texture, 1110, 50, 50, 50);
        life.flip(false, true);

        ball = new TextureRegion(texture, 840, 0, 50, 50);
        ball.flip(false, true);

        undestructableBlock = new TextureRegion(texture, 781, 0, 59, 30);
        undestructableBlock.flip(false, true);

        lightBlock = new TextureRegion(texture, 600, 0, 60, 30);
        lightBlock.flip(false, true);

        mediumBlock1 = new TextureRegion(texture, 661, 0, 59, 30);
        mediumBlock1.flip(false, true);
        mediumBlock2 = new TextureRegion(texture, 661, 30, 59, 30);
        mediumBlock2.flip(false, true);

        heavyBlock1 = new TextureRegion(texture, 721, 0, 59, 30);
        heavyBlock1.flip(false, true);
        heavyBlock2 = new TextureRegion(texture, 721, 30, 59, 30);
        heavyBlock2.flip(false, true);
        heavyBlock3 = new TextureRegion(texture, 721, 60, 59, 30);
        heavyBlock3.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        collisionSound = Gdx.audio.newSound(Gdx.files.internal("data/collision.wav"));
        gamewinSound = Gdx.audio.newSound(Gdx.files.internal("data/win.wav"));
        gameoverSound = Gdx.audio.newSound(Gdx.files.internal("data/gameover.wav"));
        bonusSound = Gdx.audio.newSound(Gdx.files.internal("data/bonus.wav"));
    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
        shadow.dispose();
    }
}
