package com.vsu.csf.arkanoid.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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
            ball.setAngle(- angle);
            ball.setPosition(ball.getPosition().x, position.y - ball.getRadius());
            health--;
        } else if (Intersector.overlaps(ball.getBoundingCircle(), bottom)) {
            ball.setAngle(- angle);
            ball.setPosition(ball.getPosition().x, position.y + height + ball.getRadius());
            health--;
        } else if (Intersector.overlaps(ball.getBoundingCircle(), left))  {
            ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
            ball.setPosition(position.x - ball.getRadius(), ball.getPosition().y);
            health--;
        } else if (Intersector.overlaps(ball.getBoundingCircle(), right)){
            ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
            ball.setPosition(position.x + width + ball.getRadius(), ball.getPosition().y);
            health--;
        }
        if (health > 0) return false;
        return true;
    }
}
