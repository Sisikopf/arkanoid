package com.vsu.csf.arkanoid.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Andrey on 19.12.2015.
 */
public class Platform {
    private Vector2 position;
    private float speed;

    private float width;
    private float height;

    private float move;

    public Platform(float x, float y, float speed, float width, float height) {
        position = new Vector2(x,  y);
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float y) {
        position = new Vector2(position.x, y);
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
}
