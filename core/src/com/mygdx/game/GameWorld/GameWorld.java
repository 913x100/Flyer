package com.mygdx.game.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.ScrollHandler;
import com.mygdx.game.GameOrganize.AssetLoader;

public class GameWorld {
    private Player player;
    private Rectangle ground;
    private ScrollHandler scroller;
    private boolean isAlive = true;
    private int score = 0;
    private int midPointY;

    private GameState currentState;
    public enum GameState {
        READY, RUNNING, GAMEOVER;
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        player = new Player(33.0F, (float)(midPointY - 5), 15, 10);
        scroller = new ScrollHandler(this,midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }


    }

    private void updateReady(float delta) {
        if(delta > .15f) {
            delta = .15f;
        }

        player.update(delta);
        scroller.update(delta);

        if(scroller.collides(player) && isAlive) {
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }

        if(Intersector.overlaps(player.getHitBox(), ground)) {
            scroller.stop();
            player.die();
            player.declerate();
        }
    }

    public void updateRunning(float delta) {
        if(delta > .15f) {
            delta = .15f;
        }

        player.update(delta);
        scroller.update(delta);

        if(scroller.collides(player) && isAlive) {
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }

        if(Intersector.overlaps(player.getHitBox(), ground)) {
            scroller.stop();
            player.die();
            player.declerate();
            currentState = GameState.GAMEOVER;
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

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        player.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
