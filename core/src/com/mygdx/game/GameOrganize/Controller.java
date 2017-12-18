package com.mygdx.game.GameOrganize;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameWorld.GameWorld;

public class Controller implements InputProcessor {
    private Player player;
    private GameWorld world;

    public Controller(GameWorld world) {
        this.world = world;
        player = world.getPlayer();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (world.isReady()) {
            world.start();
        }
        player.onTap();
        if (world.isGameOver()) {
            world.restart();
        }
        return true;
    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }
}
