package com.vsu.csf.arkanoid.gamehelpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.vsu.csf.arkanoid.gameobjects.Platform;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;

/**
 * Created by Andrey on 19.12.2015.
 */
public class InputHandler implements InputProcessor {
    private Platform platform;

    public InputHandler(Platform platform) {
        this.platform = platform;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case LEFT :
                platform.moveLeft();
                break;
            case RIGHT :
                platform.moveRight();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case LEFT :
            case RIGHT :
                platform.stop();
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
