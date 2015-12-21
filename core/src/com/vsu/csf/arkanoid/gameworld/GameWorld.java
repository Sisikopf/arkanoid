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

    public final static float BLOCK_WIDTH = 18.75f;
    public final static float BLOCK_HEIGHT = 9.375f;

    private Platform platform;
    private List<Ball> balls;
    private List<Ball> missedBalls;
    private Level level;
    private int levelNum;

    private int lives;
    private int score;

    private Rectangle top, left, right, bottom;

    public GameWorld() {
        top = new Rectangle(0, 0, GAME_WIDTH, 1);
        left = new Rectangle(0, 0, 1, GAME_HEIGHT);
        right = new Rectangle(GAME_WIDTH - 1, 0, 1, GAME_HEIGHT);
        bottom = new Rectangle(0, GAME_HEIGHT - 1, GAME_WIDTH, 1);
        balls = new ArrayList<Ball>();
        missedBalls = new ArrayList<Ball>();
        newGame();
    }

    public void newGame() {
        platform = new Platform(GAME_WIDTH / 2, GAME_HEIGHT - PLATFORM_HEIGHT / 2,
                INIT_PLATFORM_SPEED, INIT_PLATFORM_WIDTH, PLATFORM_HEIGHT);
        balls.clear();
        balls.add(new Ball(INIT_BALL_RADIUS, GAME_WIDTH / 2,
                GAME_HEIGHT - PLATFORM_HEIGHT - INIT_BALL_RADIUS, INIT_BALL_SPEED, INIT_BALL_ANGLE, platform));
        levelNum = 1;
        level = Level.loadLevel(String.format("levels/%d.txt", levelNum), BLOCK_WIDTH, BLOCK_HEIGHT);
        lives = 3;
        score = 0;
    }

    public void nextLevel() {
        levelNum++;
        level = Level.loadLevel(String.format("levels/%d.txt", levelNum), BLOCK_WIDTH, BLOCK_HEIGHT);
        balls.clear();
        balls.add(new Ball(INIT_BALL_RADIUS, GAME_WIDTH / 2,
                GAME_HEIGHT - PLATFORM_HEIGHT - INIT_BALL_RADIUS, INIT_BALL_SPEED, INIT_BALL_ANGLE, platform));
        platform.setPosition(GAME_WIDTH / 2);
    }

    private void ballWallIntersection(Ball ball) {
        float angle = ball.getAngle();

        if (Intersector.overlaps(ball.getBoundingCircle(), top)) {
            if (angle == 0 || angle == Math.PI || angle == -Math.PI)
                ball.setAngle(-(float)Math.PI / 2);
            else
                ball.setAngle(- angle);
            ball.setPosition(ball.getPosition().x, ball.getRadius());
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), platform.getBoundingRectangle())) {
            float k = (ball.getPosition().x - platform.getPosition().x) / platform.getWidth() * 2 / 3;
            angle = -angle;
            angle = angle + (angle > (float)Math.PI / 2 ? (float)Math.PI - angle : angle) * k;
            ball.setAngle(angle);
            ball.setPosition(ball.getPosition().x, GAME_HEIGHT - platform.getHeight() - ball.getRadius());
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), bottom)) {
            missedBalls.add(ball);
        }

        if (Intersector.overlaps(ball.getBoundingCircle(), left)) {
            ball.setPosition(ball.getRadius(), ball.getPosition().y);
            if (angle == Math.PI / 2 || angle == -Math.PI / 2)
                ball.setAngle(0);
            else
                ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
        } else
        if (Intersector.overlaps(ball.getBoundingCircle(), right)) {
            ball.setPosition(GAME_WIDTH - ball.getRadius(), ball.getPosition().y);
            if (angle == Math.PI / 2 || angle == -Math.PI / 2)
                ball.setAngle(-(float)Math.PI);
            else
                ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
        }
    }

    public void update(float delta) {
        //Gdx.app.log(TAG, "update");
        platform.update(delta);

        List<Block> destroyed = new ArrayList<Block>();

        missedBalls.clear();

        for (Ball ball : balls) {
            ball.update(delta);

            float ballAngle = ball.getAngle();
            for (Block block : level.getBlocks()) {
                if (block.intersect(ball, ballAngle)) {
                    score += block.getScore();
                    destroyed.add(block);
                }
            }

            ballWallIntersection(ball);
        }

        if (balls.size() - missedBalls.size() == 0) {
            lives -= 1;
            if (isGame()){
                balls.add(new Ball(INIT_BALL_RADIUS, GAME_WIDTH / 2,
                        GAME_HEIGHT - PLATFORM_HEIGHT - INIT_BALL_RADIUS, INIT_BALL_SPEED, INIT_BALL_ANGLE, platform));
                platform.setPosition(GAME_WIDTH / 2);
            }
        }

        for (Ball ball : missedBalls) {
            balls.remove(ball);
        }

        for (Block b : destroyed) {
            level.remove(b);
        }

        if (level.getDestructableBlocksCount() == 0)
            nextLevel();

        if (Intersector.overlaps(platform.getBoundingRectangle(), left)) {
            platform.setPosition(platform.getWidth() / 2);
        } else
        if (Intersector.overlaps(platform.getBoundingRectangle(), right)) {
            platform.setPosition(GAME_WIDTH - platform.getWidth() / 2);
        }

        //Gdx.app.log(TAG, String.valueOf(ball.getAngle()));
    }

    public Platform getPlatform(){
        return platform;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public List<Block> getBlocks(){
        return level.getBlocks();
    }

    public boolean isGame() {
        return lives > 0;
    }
}
