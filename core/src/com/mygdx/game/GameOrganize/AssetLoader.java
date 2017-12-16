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
    public static TextureRegion playerMid;
    public static TextureRegion playerDown;
    public static TextureRegion playerUp;
    public static TextureRegion skullUp;
    public static TextureRegion skullDown;
    public static TextureRegion bar;

    public AssetLoader() {
    }

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        // Background
        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        // Player
        playerDown = new TextureRegion(texture, 136, 0, 17, 12);
        playerDown.flip(true, true);
        playerMid = new TextureRegion(texture, 153, 0, 17, 12);
        playerMid.flip(true, true);
        playerUp = new TextureRegion(texture, 170, 0, 17, 12);
        playerUp.flip(true, true);
        TextureRegion[] players = new TextureRegion[]{ playerDown,  playerMid,  playerUp};
        playerAnimation = new Animation(0.06F, players);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public static void dispose() {
        texture.dispose();
    }
}