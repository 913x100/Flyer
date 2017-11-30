package com.mygdx.game.GameOrganize;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameObjects.Player;

public class InputHandler implements InputProcessor {
    private Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.player.onTap();
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