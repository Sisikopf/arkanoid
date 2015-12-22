package com.vsu.csf.arkanoid.gameobjects.bonus;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameobjects.Ball;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

import java.util.List;

/**
 * Created by dimko_000 on 22.12.2015.
 */
public class IncreaseBallSpeedBonus extends Bonus {
    public IncreaseBallSpeedBonus(Vector2 position) {
        this.position = position;
        this.width=SIZE;
        this.height=SIZE;
        boundingRectangle = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }
    @Override
    public void action(GameWorld world) {
        List<Ball> ballList = world.getBalls();
        for(Ball ball : ballList) {
            ball.increaseSpeed();
        }
    }
}
