package com.vsu.csf.arkanoid.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.vsu.csf.arkanoid.gamehelpers.AssetLoader;
import com.vsu.csf.arkanoid.gameobjects.*;
import com.vsu.csf.arkanoid.gameobjects.block.Block;
import com.vsu.csf.arkanoid.gameobjects.block.DestructableBlock;
import com.vsu.csf.arkanoid.gameobjects.bonus.Bonus;
import com.vsu.csf.arkanoid.gameobjects.bonus.IncreasePlatformSizeBonus;

import java.util.List;

/**
 * Created by dimko_000 on 16.12.2015.
 */
public class GameRenderer {
    private final static String TAG = "GameRenderer";

    public final float SCORE_POS_X = 11;
    public final float SCORE_POS_Y = 6;

    public final float LIVES_POS_X = GameWorld.GAME_WIDTH - 11;
    public final float LIVES_POS_Y = GameWorld.GAME_HEIGHT- 6;

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
        List<Ball> balls = world.getBalls();
        List<Block> blockList = world.getBlocks();
        List<Bonus> bonusList = world.getBonuses();
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

        // Blocks
        drawBlocks(blockList);

        batcher.enableBlending();

        // Platform
        batcher.draw(AssetLoader.platform, platform.getPosition().x - platform.getWidth() / 2,
                platform.getPosition().y - platform.getHeight() / 2, platform.getWidth(), platform.getHeight());

        // Balls
        for (Ball ball : balls)
            batcher.draw(AssetLoader.ball, ball.getPosition().x - ball.getRadius(), ball.getPosition().y - ball.getRadius(),
                ball.getRadius() * 2, ball.getRadius() * 2);

        drawBonuses(bonusList);
        drawUI();

        batcher.end();
    }

    private void drawUI() {
        if (!world.isGame()) {
            // Game Over message
            String gameOver = String.format("GAME OVER\nYOUR SCORE: %d", world.getScore());
            GlyphLayout layout = new GlyphLayout(AssetLoader.font, gameOver);

            AssetLoader.shadow.draw(batcher, gameOver, GameWorld.GAME_WIDTH / 2 - layout.width / 2 + 1,
                    GameWorld.GAME_HEIGHT / 5 * 2 + 1);
            AssetLoader.font.draw(batcher, gameOver, GameWorld.GAME_WIDTH / 2 - layout.width / 2,
                    GameWorld.GAME_HEIGHT / 5 * 2);
        }
        else {
            // Score
            String score = String.valueOf("Score: " + world.getScore());
            AssetLoader.shadow.draw(batcher, score, SCORE_POS_X + 1, SCORE_POS_Y + 1);
            AssetLoader.font.draw(batcher, score, SCORE_POS_X, SCORE_POS_Y);
            //
            String lives = String.valueOf("Lives: " + world.getLives());
            AssetLoader.shadow.draw(batcher, lives, 3*(LIVES_POS_X + 1)/4, SCORE_POS_Y + 1);
            AssetLoader.font.draw(batcher, lives, 3*LIVES_POS_X/4, SCORE_POS_Y);
        }
    }
    private void drawBlocks(List<Block> blockList) {
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
    }

    private void drawBonuses(List<Bonus> bonusList) {
        for (Bonus bonus : bonusList) {
            TextureRegion region = AssetLoader.heavyBlock3;
            batcher.draw(region, bonus.getPosition().x, bonus.getPosition().y, bonus.getWidth(), bonus.getHeight());
        }
    }
}
