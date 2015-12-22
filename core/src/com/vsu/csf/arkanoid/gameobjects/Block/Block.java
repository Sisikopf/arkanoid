package com.vsu.csf.arkanoid.gameobjects.block;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameobjects.Ball;

/**
 * Created by Andrey on 19.12.2015.
 */
public abstract class Block {
    protected Vector2 position;
    protected float width;
    protected float height;

    protected Rectangle top, left, right, bottom;

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public abstract boolean intersect(Ball ball, float angle);  // returns 'true' if block is destroyed

    public abstract int getScore();
}
