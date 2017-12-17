package com.mygdx.game.GameWorld;

import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.ScrollHandler;
import com.mygdx.game.GameOrganize.AssetLoader;

public class GameWorld {
    private Player player;
    private ScrollHandler scroller;
    private boolean isAlive;
    private int score;

    public GameWorld(int midPointY) {
        player = new Player(33.0F, (float)(midPointY - 5), 15, 10);
        scroller = new ScrollHandler(this,midPointY + 66);
        isAlive = true;
        score = 0;
    }

    public void update(float delta) {
        player.update(delta);
        scroller.update(delta);

        if(scroller.collides(player) && isAlive) {
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int add) {
        score += add;
    }
}
