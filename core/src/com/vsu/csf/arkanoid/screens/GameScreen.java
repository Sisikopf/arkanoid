package com.vsu.csf.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.vsu.csf.arkanoid.gamehelpers.InputHandler;
import com.vsu.csf.arkanoid.gameworld.GameRenderer;
import com.vsu.csf.arkanoid.gameworld.GameWorld;

/**
 * Created by dimko_000 on 16.12.2015.
 */
public class GameScreen implements Screen{
    private final static String TAG = "GameScreen";
    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {
        Gdx.app.log(TAG,"Attached");
        world = new GameWorld();
        renderer = new GameRenderer(world);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show called");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG,"resizing");
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "resume called");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause called");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "hide called");
    }

    @Override
    public void dispose() {

    }
}
