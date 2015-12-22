package com.vsu.csf.arkanoid.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

import static com.badlogic.gdx.math.MathUtils.*;

/**
 * Created by Andrey on 19.12.2015.
 */
public class Ball {
    private float radius;
    private Vector2 position;
    private float speed;
    private float angle;

    private Platform platform;
    private Circle boundingCircle;

    private boolean move;

    public Ball(float radius, float x, float y, float speed, float angle, Platform platform) {
        this.radius = radius;
        this.position = new Vector2(x, y);
        this.speed = speed;
        this.angle = angle;
        move = false;
        boundingCircle = new Circle();
        this.platform = platform;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position = new Vector2(x, y);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void update(float delta) {
        if (move) {
            position.x += speed * cos(angle) * delta;
            position.y -= speed * sin(angle) * delta;
        } else {
            position.x = platform.getPosition().x;
        }
        boundingCircle.set(position.x, position.y, radius);
    }

    public void start() {
        move = true;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void increaseSpeed() {
        if(speed< GameWorld.INIT_BALL_SPEED*4)
        speed*=2;
    }
    public void decreaseSpeed() {
        if(speed> GameWorld.INIT_BALL_SPEED/4)
            speed/=2;
    }
}
