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

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0.0F, 0.0F);
        acceleration = new Vector2(0.0F, 460.0F);
        hitBox = new Circle();
    }

    public void update(float delta) {
        velocity.add(this.acceleration.cpy().scl(delta));
        if (velocity.y > 180.0F) {
            velocity.y = 180.0F;
        }
        position.add(this.velocity.cpy().scl(delta));

        hitBox.set(position.x + 9, position.y + 6, 6.0F);



    }

    public void onTap() {
        velocity.y = -140.0F;
    }

    public boolean isFalling() {
        return this.velocity.y > 100;
    }

    public boolean shouldntFlap() {
        return this.velocity.y > 70;
    }

    public float getX() {
        return this.position.x;
    }

    public Circle getHitBox() {
        return hitBox;
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
