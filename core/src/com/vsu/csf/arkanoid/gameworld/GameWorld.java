package com.vsu.csf.arkanoid.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.vsu.csf.arkanoid.gameobjects.Ball;
import com.vsu.csf.arkanoid.gameobjects.Block;
import com.vsu.csf.arkanoid.gameobjects.Level;
import com.vsu.csf.arkanoid.gameobjects.Platform;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

/**
 * Created by dimko_000 on 16.12.2015.
 */
public class GameWorld {
    private final static String TAG = "GameWorld";

    public final static float GAME_HEIGHT = 300;
    public final static float GAME_WIDTH = 300;

    public final static float PLATFORM_HEIGHT = 5;
    public final static float INIT_PLATFORM_WIDTH = 60;
    public final static float INIT_PLATFORM_SPEED = 200;

    public final static float INIT_BALL_RADIUS = 5;
    public final static float INIT_BALL_SPEED = 200;
    public final static float INIT_BALL_ANGLE = (float)Math.PI / 2;

    public final static float BLOCK_WIDTH = 30;
    public final static float BLOCK_HEIGHT = 15;

    private Platform platform;
    private Ball ball;
    private Level level;

    private int lives;
    private int score;

    private Rectangle top, left, right, bottom;

    public GameWorld() {
        platform = new Platform(GAME_WIDTH / 2, GAME_HEIGHT - PLATFORM_HEIGHT / 2,
                INIT_PLATFORM_SPEED, INIT_PLATFORM_WIDTH, PLATFORM_HEIGHT);
        ball = new Ball(INIT_BALL_RADIUS, GAME_WIDTH / 2,
                GAME_HEIGHT - PLATFORM_HEIGHT - INIT_BALL_RADIUS, INIT_BALL_SPEED, INIT_BALL_ANGLE, platform);
        level = Level.loadLevel("level.txt", BLOCK_WIDTH, BLOCK_HEIGHT);
        top = new Rectangle(0, 0, GAME_WIDTH, 1);
        left = new Rectangle(0, 0, 1, GAME_HEIGHT);
        right = new Rectangle(GAME_WIDTH - 1, 0, 1, GAME_HEIGHT);
        bottom = new Rectangle(0, GAME_HEIGHT - 1, GAME_WIDTH, 1);

        lives = 3;
        score = 0;
    }

    public void update(float delta) {
        //Gdx.app.log(TAG, "update");
        platform.update(delta);
        ball.update(delta);

        float angle = ball.getAngle();

        List<Block> destroyed = new ArrayList<Block>();

        for (Block b : level.getBlocks()) {
            if (b.intersect(ball)) {
                score += b.getScore();
                destroyed.add(b);
            }
        }

        for (Block b : destroyed){
            level.remove(b);
        }

        if (Intersector.overlaps(ball.getBoundingCircle(), top)) {
            ball.setAngle(- angle);
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), platform.getBoundingRectangle())) {
            float k = (ball.getPosition().x - platform.getPosition().x) / platform.getWidth() * 2 / 3;
            angle = -angle;
            angle = angle + (angle > (float)Math.PI / 2 ? (float)Math.PI - angle : angle) * k;
            ball.setAngle(angle);
            ball.setPosition(ball.getPosition().x, GAME_HEIGHT - platform.getHeight() - ball.getRadius());
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), left)) {
            ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), right)) {
            ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), bottom)) {
            lives -= 1;
            ball = new Ball(INIT_BALL_RADIUS, GAME_WIDTH / 2,
                    GAME_HEIGHT - PLATFORM_HEIGHT - INIT_BALL_RADIUS, INIT_BALL_SPEED, INIT_BALL_ANGLE, platform);
            platform.setPosition(GAME_WIDTH / 2);
        }

        if (Intersector.overlaps(platform.getBoundingRectangle(), left)) {
            platform.setPosition(platform.getWidth() / 2);
        } else
        if (Intersector.overlaps(platform.getBoundingRectangle(), right)) {
            platform.setPosition(GAME_WIDTH - platform.getWidth() / 2);
        }

        Gdx.app.log(TAG, String.valueOf(ball.getAngle()));
    }

    public Platform getPlatform(){
        return platform;
    }

    public Ball getBall() {
        return ball;
    }

    public int getLives() {
        return lives;
    }

    public List<Block> getBlocks(){
        return level.getBlocks();
    }
}
