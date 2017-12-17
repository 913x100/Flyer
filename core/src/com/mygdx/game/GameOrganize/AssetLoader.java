package com.mygdx.game.GameOrganize;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion grass;
    public static Animation playerAnimation;
    public static TextureRegion playerMid, playerDown, playerUp;
    public static TextureRegion skullRight, skullLeft;
    public static TextureRegion bar;
    public static Sound dead, coin;
    public static BitmapFont font, shadow, problem;

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

        // Sound
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        // Font
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        problem = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        problem.getData().setScale(.15f, -.15f);
    }

    public static void dispose() {
        texture.dispose();
        dead.dispose();
        font.dispose();
    }
}