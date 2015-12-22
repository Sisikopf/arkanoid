package com.vsu.csf.arkanoid.gameobjects.block;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameobjects.Ball;

import static com.badlogic.gdx.math.MathUtils.sin;

/**
 * Created by Andrey on 19.12.2015.
 */
public class UndestructableBlock extends Block {

    public UndestructableBlock(float x, float y, float width, float height) {
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;

        top = new Rectangle(x, y, width, 1);
        left = new Rectangle(x, y, 1, height);
        right = new Rectangle(x + width - 1, y, 1, height);
        bottom = new Rectangle(x, y + height - 1, width, 1);
    }

    @Override
    public boolean intersect(Ball ball, float angle) {
        if (Intersector.overlaps(ball.getBoundingCircle(), top)) {
            ball.setAngle(- angle);
            ball.setPosition(ball.getPosition().x, position.y - ball.getRadius());
        } else if (Intersector.overlaps(ball.getBoundingCircle(), bottom)) {
            ball.setAngle(- angle);
            ball.setPosition(ball.getPosition().x, position.y + height + ball.getRadius());
        } else if (Intersector.overlaps(ball.getBoundingCircle(), left))  {
            ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
            ball.setPosition(position.x - ball.getRadius(), ball.getPosition().y);
        } else if (Intersector.overlaps(ball.getBoundingCircle(), right)){
            ball.setAngle(Math.abs((float)Math.PI - Math.abs(angle)) * Math.signum(sin(angle)));
            ball.setPosition(position.x + width + ball.getRadius(), ball.getPosition().y);
        }
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
