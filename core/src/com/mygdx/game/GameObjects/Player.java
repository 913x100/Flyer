package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameOrganize.AssetLoader;

public class Player {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    private boolean playerSide;
    private TextureRegion playerUp, playerMid, playerDown;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0.0F, 0.0F);
        this.acceleration = new Vector2(0.0F, 460.0F);
        this.playerSide = true;
        this.velocity.x = 20.0F;
        this.playerUp = AssetLoader.playerUp;
        this.playerMid = AssetLoader.playerMid;
        this.playerDown = AssetLoader.playerDown;
    }

    public void update(float delta) {
        this.velocity.add(this.acceleration.cpy().scl(delta));

        if(getX() > 120) {
            this.velocity.x = -50.0F;
            this.velocity.y = 10.0F;
            this.playerSide = !this.playerSide;
            setSide();
        }
        if(getX() < 0) {
            this.velocity.x = 50.0F;
            this.velocity.y = 10.0F;
            this.playerSide = !this.playerSide;
            setSide();
        }

        if (this.velocity.y > 120.0F) {
            this.velocity.y = 120.0F;
        }

        this.position.add(this.velocity.cpy().scl(delta));

        // Rotate ccw
        /*if(this.velocity.y < 0) {
            rotation -= 600*delta;
            if(rotation < - 20) {
                rotation = -20;
            }
        }

        // Rotate cw
        if(isFalling()) {
            rotation += 480*delta;
            if(rotation > 90) {
                rotation = 90;
            }
        }*/
    }

    public void onTap() {
        this.velocity.y = -140.0F;
        if(getSide()) {
            this.velocity.x = -50.0F;
        } else {
            this.velocity.x = 50.0F;
        }
    }

    public void setSide() {
        playerUp.flip(true,false);
        playerMid.flip(true,false);
        playerDown.flip(true,false);
    }

    public boolean isFalling() {
        return this.velocity.y > 100;
    }

    public boolean shouldntFlap() {
        return this.velocity.y > 70;
    }

    public boolean getSide() {
        return this.playerSide;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public float getWidth() {
        return (float)this.width;
    }

    public float getHeight() {
        return (float)this.height;
    }

    public float getRotation() {
        return this.rotation;
    }

}
