package com.vsu.csf.arkanoid.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.vsu.csf.arkanoid.gamehelpers.AssetLoader;
import com.vsu.csf.arkanoid.gameobjects.*;

import java.util.List;

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
        //Gdx.app.log(TAG, "render");

        Platform platform = world.getPlatform();
        Ball ball = world.getBall();
        List<Block> blockList = world.getBlocks();

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

        for (Block b : blockList) {
            TextureRegion region = AssetLoader.undestructableBlock;
            if (b.getClass().equals(DestructableBlock.class)){
                switch (((DestructableBlock)b).getBlockType()) {
                    case LIGHT: region = AssetLoader.lightBlock; break;
                    case MEDIUM:
                        if (((DestructableBlock) b).getHealth() == 2)
                            region = AssetLoader.mediumBlock1;
                        else
                            region = AssetLoader.mediumBlock2;
                        break;
                    case HEAVY:
                        if (((DestructableBlock) b).getHealth() == 3)
                            region = AssetLoader.heavyBlock1;
                        else if (((DestructableBlock) b).getHealth() == 2)
                            region = AssetLoader.heavyBlock2;
                        else
                            region = AssetLoader.heavyBlock3;
                        break;
                }
            }
            batcher.draw(region, b.getPosition().x, b.getPosition().y, b.getWidth(), b.getHeight());
        }

        batcher.enableBlending();

        batcher.draw(AssetLoader.platform, platform.getPosition().x - platform.getWidth() / 2,
                platform.getPosition().y - platform.getHeight() / 2, platform.getWidth(), platform.getHeight());

        batcher.draw(AssetLoader.ball, ball.getPosition().x - ball.getRadius(), ball.getPosition().y - ball.getRadius(),
                ball.getRadius() * 2, ball.getRadius() * 2);

        batcher.end();
    }
}
