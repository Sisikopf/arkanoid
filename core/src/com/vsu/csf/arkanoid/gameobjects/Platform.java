package com.vsu.csf.arkanoid.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

/**
 * Created by Andrey on 19.12.2015.
 */
public class Platform {
    private Vector2 position;
    private float speed;

    private float width;
    private float height;

    private Rectangle boundingRectangle;

    private float move;

    public Platform(float x, float y, float speed, float width, float height) {
        position = new Vector2(x,  y);
        this.speed = speed;
        this.width = width;
        this.height = height;

        boundingRectangle = new Rectangle();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x) {
        position = new Vector2(x, position.y);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public void update(float delta) {
        position.x += speed * move * delta;
        resetBoundingRectangle();
    }

    public void moveLeft() {
        move = -1;
    }

    public void moveRight() {
        move = 1;
    }

    public void stop() {
        move = 0;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void increaseSize() {
        if(width<GameWorld.INIT_PLATFORM_WIDTH*4) {
            width *= 2;
            resetBoundingRectangle();
        }
    }
    public void decreaseSize() {
        if(width>GameWorld.INIT_PLATFORM_WIDTH/4) {
            width /= 2;
            resetBoundingRectangle();
        }
    }
    private void resetBoundingRectangle() {
        boundingRectangle.set(position.x - width / 2, position.y - height / 2, width, height);
    }
}
