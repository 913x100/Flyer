package com.mygdx.game.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0.0F, 0.0F);
        this.acceleration = new Vector2(0.0F, 460.0F);
    }

    public void update(float delta) {
        this.velocity.add(this.acceleration.cpy().scl(delta));
        if (this.velocity.y > 200.0F) {
            this.velocity.y = 200.0F;
        }

        this.position.add(this.velocity.cpy().scl(delta));
    }

    public void onTap() {
        this.velocity.y = -140.0F;
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
