package com.mygdx.game.GameWorld;

import com.mygdx.game.GameObjects.Player;

public class GameWorld {
    private Player player;

    public GameWorld(int midPointY) {
        this.player = new Player(33.0F, (float)(midPointY - 5), 17, 12);
    }

    public void update(float delta) {
        this.player.update(delta);
    }

    public Player getPlayer() {
        return this.player;
    }
}
