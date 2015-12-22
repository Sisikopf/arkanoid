package com.vsu.csf.arkanoid.gameobjects.block;

/**
 * Created by Andrey on 19.12.2015.
 */
public enum BlockType {
    LIGHT, MEDIUM, HEAVY;

    public static int getHealth(BlockType type) {
        switch (type) {
            case LIGHT : return 1;
            case MEDIUM: return 2;
            case HEAVY: return 3;
        }
        return 0;
    }

    public static int getScore(BlockType type) {
        switch (type) {
            case LIGHT : return 10;
            case MEDIUM: return 30;
            case HEAVY: return 50;
        }
        return 0;
    }
}
