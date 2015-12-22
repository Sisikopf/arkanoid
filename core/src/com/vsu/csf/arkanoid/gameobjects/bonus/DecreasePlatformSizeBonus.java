package com.vsu.csf.arkanoid.gameobjects.bonus;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

/**
 * Created by dimko_000 on 22.12.2015.
 */
public class DecreasePlatformSizeBonus extends Bonus {
    public DecreasePlatformSizeBonus(Vector2 position) {
        this.position = position;
        this.width=SIZE;
        this.height=SIZE;
        boundingRectangle = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }
    @Override
    public void action(GameWorld world) {
        world.getPlatform().decreaseSize();
    }
}
