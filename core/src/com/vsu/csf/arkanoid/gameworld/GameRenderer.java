package com.vsu.csf.arkanoid.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.vsu.csf.arkanoid.gamehelpers.AssetLoader;
import com.vsu.csf.arkanoid.gameobjects.Platform;

/**
 * Created by dimko_000 on 16.12.2015.
 */
public class GameRenderer {
    private final static String TAG = "GameRenderer";

    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld world) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 300, 300);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render() {
        Gdx.app.log(TAG, "render");

        Platform platform = world.getPlatform();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // background
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(0, 0, GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT);

        shapeRenderer.end();

        batcher.begin();

        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, 0, GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT);

        batcher.enableBlending();
        batcher.draw(AssetLoader.platform, platform.getPosition().x - platform.getWidth() / 2,
                platform.getPosition().y - platform.getHeight(), platform.getWidth(), platform.getHeight());

        batcher.end();
    }
}
