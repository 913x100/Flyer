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
    public static Animation birdAnimation;
    public static Texture playerMid;
    public static Texture playerDown;
    public static Texture playerUp;
    public static TextureRegion skullUp;
    public static TextureRegion skullDown;
    public static TextureRegion bar;

    public AssetLoader() {
    }

    public static void load() {
        texture = new Texture(Gdx.files.internal("images/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);
        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);
        playerDown = new Texture(Gdx.files.internal("images/redbird-downflap.png"));
        playerMid = new Texture(Gdx.files.internal("images/redbird-midflap.png"));
        playerUp = new Texture(Gdx.files.internal("images/redbird-upflap.png"));
        //birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        //birdDown.flip(false, true);
        //bird = new TextureRegion(texture, 153, 0, 17, 12);
        //bird.flip(false, true);
        //birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        //birdUp.flip(false, true);
        Texture[] birds = new Texture[]{playerDown, playerMid, playerUp};
        birdAnimation = new Animation(0.06F, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);
        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
    }

    public static void dispose() {
        texture.dispose();
    }
}