package com.vsu.csf.arkanoid.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Andrey on 19.12.2015.
 */
public abstract class Block {
    protected Vector2 position;
    protected float width;
    protected float height;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract boolean intersect(Ball ball);  // returns 'true' if block is destroyed

    public abstract int getScore();
}
