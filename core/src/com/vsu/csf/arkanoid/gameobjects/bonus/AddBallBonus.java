package com.vsu.csf.arkanoid.gameobjects.bonus;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameobjects.Ball;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

import java.util.List;

/**
 * Created by dimko_000 on 22.12.2015.
 */
public class AddBallBonus extends Bonus {
    float speed;
    float angle;
    public AddBallBonus(Vector2 position,float speed, float angle) {
        this.position = position;
        this.width=SIZE;
        this.height=SIZE;
        this.speed = speed;
        this.angle = angle;
        boundingRectangle = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }
    @Override
    public void action(GameWorld world) {
        Ball ball = new Ball(GameWorld.INIT_BALL_RADIUS, GameWorld.GAME_WIDTH/2, 3*GameWorld.GAME_HEIGHT/4,speed, angle,world.getPlatform());
        world.getAddedBalls().add(ball);
    }
}
