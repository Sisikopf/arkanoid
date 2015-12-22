package com.vsu.csf.arkanoid.gameobjects.bonus;

import com.badlogic.gdx.math.Vector2;
import com.vsu.csf.arkanoid.gameobjects.bonus.Bonus;
import com.vsu.csf.arkanoid.gameobjects.bonus.IncreasePlatformSizeBonus;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by dimko_000 on 22.12.2015.
 */
public class BonusFactory {
    private Random random;
    public BonusFactory(Random random) {
        this.random = random;
    }
    public Bonus getBonus(Vector2 position) {
        int rnd = random.nextInt(6);
        switch (rnd) {
            case 0:
                return new IncreasePlatformSizeBonus(position);
            case 1:
                return new DecreasePlatformSizeBonus(position);
            case 2:
                return new IncreaseBallSpeedBonus(position);
            case 3:
                return new DecreaseBallSpeedBonus(position);
            case 4:
                return new AddLifeBonus(position);
            case 5:
                return new AddBallBonus(position, GameWorld.INIT_BALL_SPEED,(float)random.nextDouble()+0.5f);
            default:
                return null;
        }
    }
}
