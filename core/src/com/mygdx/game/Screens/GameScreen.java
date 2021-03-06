package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.GameOrganize.Controller;
import com.mygdx.game.GameWorld.GameRenderer;
import com.mygdx.game.GameWorld.GameWorld;


public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {
        float screenWidth = (float)Gdx.graphics.getWidth();
        float screenHeight = (float)Gdx.graphics.getHeight();
        float gameWidth = 136.0F;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int)(gameHeight / 2.0F);
        world = new GameWorld(midPointY);
        renderer = new GameRenderer(this.world, (int)gameHeight, midPointY);
        Gdx.input.setInputProcessor(new Controller(world));
    }

    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
    }

    public void show() {
        System.out.println("GameScreen - show called");
    }

    public void hide() {
        System.out.println("GameScreen - hide called");
    }

    public void pause() {
        System.out.println("GameScreen - pause called");
    }

    public void resume() {
        System.out.println("GameScreen - resume called");
    }

    public void dispose() {
    }
}
