package com.mygdx.game.GameOrganize;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg;
    public static TextureRegion grass;
    public static Animation playerAnimation;
    public static Texture playerMid;
    public static Texture playerDown;
    public static Texture playerUp;
    public static TextureRegion skullUp;
    public static TextureRegion skullDown;
    public static TextureRegion bar;

    public AssetLoader() {
    }

    public static void load() {
        playerDown = new Texture(Gdx.files.internal("images/redbird-downflap.png"));
        playerMid = new Texture(Gdx.files.internal("images/redbird-midflap.png"));
        playerUp = new Texture(Gdx.files.internal("images/redbird-upflap.png"));
        Texture[] birds = new Texture[]{playerDown, playerMid, playerUp};
        playerAnimation = new Animation(0.06F, birds);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public static void dispose() {
        texture.dispose();
    }
}