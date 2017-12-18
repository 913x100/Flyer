package com.mygdx.game.GameObjects;

import com.mygdx.game.GameOrganize.AssetLoader;
import com.mygdx.game.GameWorld.GameWorld;

public class ScrollHandler {
    private GameWorld gameWorld;
    private Pipe pipe1, pipe2, pipe3;
    public static final int SCROLL_SPEED = -49;
    public static final int PIPE_GAP = 65;


    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        pipe1 = new Pipe(130, 60, 18, 35, SCROLL_SPEED);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 50, 18, 35, SCROLL_SPEED);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 70, 18, 35, SCROLL_SPEED);
    }

    public void update(float delta) {
        pipeUpdate(delta);

        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);
        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }

    }

    public void stop() {
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    public boolean collides(Player player) {
        if (!pipe1.isScored()
                && pipe1.getX() + (pipe1.getWidth() / 2) < player.getX() + player.getWidth()) {

            addScore(1);
            pipe1.setScored(true);
            AssetLoader.coin.play();
        } else if (!pipe2.isScored()
                && pipe2.getX() + (pipe2.getWidth() / 2) < player.getX()
                + player.getWidth()) {
            addScore(1);
            pipe2.setScored(true);
            AssetLoader.coin.play();

        } else if (!pipe3.isScored()
                && pipe3.getX() + (pipe3.getWidth() / 2) < player.getX()
                + player.getWidth()) {
            addScore(1);
            pipe3.setScored(true);
            AssetLoader.coin.play();

        }
        return (pipe1.collides(player) || pipe2.collides(player) || pipe3.collides(player));
    }

    public void onRestart() {
        pipe1.onRestart(210, SCROLL_SPEED);
        pipe2.onRestart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
        pipe3.onRestart(pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

    private void pipeUpdate(float delta) {
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);
    }

}