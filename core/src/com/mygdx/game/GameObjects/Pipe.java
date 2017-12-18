package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameOrganize.AssetLoader;

import java.util.Random;

public class Pipe extends Scrollable {
    private SpriteBatch batcher;
    private Random r;
    private Rectangle pipeUp, pipeMid, pipeDown, barUp, barMid1, barMid2, barDown;
    private boolean isScored = false;
    private boolean isAnswerUp = false;
    private int opd1, opd2;
    private String problem, operator, ansUp, ansDown;

    public static final int PIPE_GAP = 40;
    public static final int BAR_WIDTH = 24;
    public static final int BAR_HEIGHT = 5;

    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();

        pipeUp = new Rectangle();
        pipeMid = new Rectangle();
        pipeDown = new Rectangle();
        barUp = new Rectangle();
        barMid1 = new Rectangle();
        barMid2 = new Rectangle();
        barDown = new Rectangle();

        problem = setProblem();
        batcher = new SpriteBatch();

        int rand = r.nextInt(2);
        if(rand == 0) {
            isAnswerUp = true;
            setAnsUp(0, 0);
            setAnsDown(r.nextInt(5)+1, r.nextInt(5)+1);
        } else {
            isAnswerUp = false;
            setAnsUp(r.nextInt(5)+1, r.nextInt(5)+1);
            setAnsDown(0, 0);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        pipeMid.set(position.x, position.y, width, height);
        if(isAnswerUp) {
            pipeUp.set(position.x, 0, width, position.y - 40);
            pipeDown.set(position.x, position.y + height, width, 50);
        } else {
            pipeUp.set(position.x, 0, width, position.y + height);
            pipeDown.set(position.x, position.y + height + PIPE_GAP, width, 50);
        }

        barUp.set(position.x - (BAR_WIDTH - width) / 2,  position.y - 40 - BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
        barMid1.set(position.x - (BAR_WIDTH - width) / 2, position.y, BAR_WIDTH, BAR_HEIGHT);
        barMid2.set(position.x - (BAR_WIDTH - width) / 2, position.y + height - BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
        barDown.set(position.x - (BAR_WIDTH - width) / 2, position.y + height + PIPE_GAP - BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);

        this.height = 35;
        this.position.y = this.r.nextInt(30) + 50;
        isScored = false;
    }

    public void onRestart(float x, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }

    public boolean collides(Player player) {
        if(position.x < player.getX() + player.getWidth()) {
            return (Intersector.overlaps(player.getHitBox(), pipeUp)
            || Intersector.overlaps(player.getHitBox(), pipeMid)
            || Intersector.overlaps(player.getHitBox(), pipeDown));
        }
        return false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }

    private String setProblem() {
        opd1 = r.nextInt(11)+1;
        opd2 = r.nextInt(11)+1;

        switch (r.nextInt(2)) {
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "x";
                break;
        }
        return opd1 + operator + opd2;
    }

    private String setAnsUp(int a, int b) {
        if(operator == "+") {
            ansUp = String.valueOf((opd1+a)+(opd2+b));
        } else if(operator == "x") {
            ansUp = String.valueOf((opd1+a)*(opd2+b));
        }
        return ansUp;
    }

    private String setAnsDown(int a, int b) {
        if(operator == "+") {
            ansDown = String.valueOf((opd1+a)+(opd2+b));
        } else if(operator == "x") {
            ansDown = String.valueOf((opd1+a)*(opd2+b));
        }
        return ansDown;
    }

    public String getAnsUp() {
        return ansUp;
    }

    public String getAnsDown() {
        return ansDown;
    }

    public Rectangle getPipeUp() {
        return pipeUp;
    }

    public Rectangle getPipeMid() {
        return pipeMid;
    }

    public Rectangle getPipeDown() {
        return pipeDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarMid1() {
        return barMid1;
    }

    public Rectangle getBarMid2() {
        return barMid2;
    }

    public Rectangle getBarDown() {
        return barDown;
    }

    public String getProblem() {
        return problem;
    }
}
