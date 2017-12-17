package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameOrganize.AssetLoader;

public class Player {
    private Vector2 position, velocity, acceleration;
    private float rotation;
    private int width, height;
    private Circle hitBox;
    private boolean isAlive;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0.0F, 0.0F);
        acceleration = new Vector2(0.0F, 460.0F);
        hitBox = new Circle();
        isAlive = true;
    }

    public void update(float delta) {
        velocity.add(this.acceleration.cpy().scl(delta));
        if (velocity.y > 180.0F) {
            velocity.y = 180.0F;
        }
        if(position.y < -10) {
            position.y = -10;
            velocity.y = 0;
        }

        position.add(this.velocity.cpy().scl(delta));

        hitBox.set(position.x + 9 , position.y + 6 , 6.0F);

    }

    public void onTap() {
        if(isAlive) {
            velocity.y = -140.0F;
        }
    }

    public boolean isFalling() {
        return velocity.y > 100;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70;
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }

    public void declerate() {
        acceleration.y = 0;
    }

    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

    public float getX() {
        return position.x;
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return (float)width;
    }

    public float getHeight() {
        return (float)height;
    }

}
