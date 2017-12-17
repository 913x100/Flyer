package com.mygdx.game.GameObjects;

import java.util.Random;

public class Pipe extends Scrollable {
    private Random r;

    public Pipe(float x, float y, int width, int height, int upPipeHeight, float scrollSpeed) {
        super(x, y, width, height, upPipeHeight,  scrollSpeed);
        r = new Random();
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);

        this.height = 35;
        this.position.y = this.r.nextInt(30) + 50;

    }

}
