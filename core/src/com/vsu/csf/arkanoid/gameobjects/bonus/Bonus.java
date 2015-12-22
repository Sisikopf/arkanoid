package com.vsu.csf.arkanoid.gameobjects.bonus;

import com.badlogic.gdx.math.*;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

import java.awt.*;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by dimko_000 on 22.12.2015.
 */
public abstract class Bonus {
    protected static int SPEED = 100;
    protected static float SIZE = 12f;
    protected Vector2 position;
    protected float width;
    protected float height;
    protected Rectangle boundingRectangle;

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public float getHeight() {
        return height;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void update(double delta) {
        position.y+=delta*SPEED;
        boundingRectangle.set(position.x - width / 2, position.y - height / 2, width, height);
    }
    public abstract void action(GameWorld world);

}
