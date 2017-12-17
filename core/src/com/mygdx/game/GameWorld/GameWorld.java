package com.mygdx.game.GameWorld;

import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.ScrollHandler;

public class GameWorld {
    private Player player;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        this.player = new Player(33.0F, (float)(midPointY - 5), 15, 10);
        this.scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        this.player.update(delta);
        scroller.update(delta);
    }

    public Player getPlayer() {
        return this.player;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }
}
