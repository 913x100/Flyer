package com.mygdx.game.GameOrganize;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion grass;
    public static Animation playerAnimation;
    public static TextureRegion playerMid, playerDown, playerUp;
    public static TextureRegion skullRight, skullLeft;
    public static TextureRegion bar;

    public AssetLoader() {
    }

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // Player
        playerDown = new TextureRegion(texture, 136, 0, 17, 12);
        playerDown.flip(false, true);
        playerMid = new TextureRegion(texture, 153, 0, 17, 12);
        playerMid.flip(false, true);
        playerUp = new TextureRegion(texture, 170, 0, 17, 12);
        playerUp.flip(false, true);
        TextureRegion[] players = new TextureRegion[]{ playerDown,  playerMid,  playerUp};
        playerAnimation = new Animation(0.06F, players);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        // Pipe
        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(true, true);

    }

    public static void dispose() {
        texture.dispose();
    }
}