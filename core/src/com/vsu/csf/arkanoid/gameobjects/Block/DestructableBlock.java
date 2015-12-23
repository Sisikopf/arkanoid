package com.vsu.csf.arkanoid.gameobjects.block;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gamehelpers.AssetLoader;
import com.vsu.csf.arkanoid.gameobjects.Ball;

import static com.badlogic.gdx.math.MathUtils.sin;

/**
 * Created by Andrey on 19.12.2015.
 */
public class DestructableBlock extends Block {
    private final static String TAG = "DestructableBlock";
    private int health;
    private int score;
    private BlockType type;

    public DestructableBlock(float x, float y, float width, float height, BlockType type) {
        position = new Vector2(x, y);

        this.width = width;
        this.height = height;

        this.type = type;
        health = BlockType.getHealth(type);
        score = BlockType.getScore(type);

        top = new Rectangle(x, y, width, 1);
        left = new Rectangle(x, y, 1, height);
        right = new Rectangle(x + width - 1, y, 1, height);
        bottom = new Rectangle(x, y + height - 1, width, 1);
    }

    public int getHealth() {
        return health;
    }

    public BlockType getBlockType() {
        return type;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean intersect(Ball ball, float angle) {
        if (Intersector.overlaps(ball.getBoundingCircle(), top)) {
            if (Intersector.overlaps(ball.getBoundingCircle(), left)) {
                ball.setAngle((float)Math.PI * Math.signum(-angle) + angle);
            } else if (Intersector.overlaps(ball.getBoundingCircle(), right)) {
                ball.setAngle((float)Math.PI * Math.signum(-angle) + angle);
            } else {
                ball.setAngle(-angle);
                ball.setPosition(ball.getPosition().x, position.y - ball.getRadius());
            }
            health--;
            AssetLoader.collisionSound.play();
        } else if (Intersector.overlaps(ball.getBoundingCircle(), bottom)) {
            if (Intersector.overlaps(ball.getBoundingCircle(), left)) {
                ball.setAngle((float)Math.PI * Math.signum(-angle) + angle);
            } else if (Intersector.overlaps(ball.getBoundingCircle(), right)) {
                ball.setAngle((float)Math.PI * Math.signum(-angle) + angle);
            } else {
                ball.setAngle(-angle);
                ball.setPosition(ball.getPosition().x, position.y + height + ball.getRadius());
            }
            health--;
            AssetLoader.collisionSound.play();
        } else if (Intersector.overlaps(ball.getBoundingCircle(), left))  {
            if (angle == 0) ball.setAngle((float)Math.PI);
            else ball.setAngle(Math.signum(angle) * (float)Math.PI - angle);
            ball.setPosition(position.x - ball.getRadius(), ball.getPosition().y);
            health--;
            AssetLoader.collisionSound.play();
        } else if (Intersector.overlaps(ball.getBoundingCircle(), right)){
            if (angle == Math.PI || angle == -Math.PI)
                ball.setAngle(0);
            else ball.setAngle(Math.signum(angle) * (float)Math.PI - angle);
            ball.setPosition(position.x + width + ball.getRadius(), ball.getPosition().y);
            health--;
            AssetLoader.collisionSound.play();
        }
        if (health > 0) return false;
        return true;
    }
}
