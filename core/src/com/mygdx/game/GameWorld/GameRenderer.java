package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameObjects.Pipe;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.ScrollHandler;
import com.mygdx.game.GameOrganize.AssetLoader;


public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private int midPointY;
    private int gameHeight;

    // Game Objects
    private Player player;
    private ScrollHandler scroller;
    private Pipe pipe1, pipe2, pipe3;

    // Game Asset
    private TextureRegion grass;
    private TextureRegion playerUp, playerMid, playerDown;
    private TextureRegion skullRight, skullLeft, bar;
    private Animation playerAnimation;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.myWorld = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(true, 136.0F, (float)gameHeight);
        this.batcher = new SpriteBatch();
        this.batcher.setProjectionMatrix(this.cam.combined);

        initGameObjects();
        initAssets();
    }

    private void drawPipes() {
        this.batcher.draw(this.bar, this.pipe1.getX(), 0,
                (float)this.pipe1.getWidth(), (float)(this.pipe1.getY() - 40));
        this.batcher.draw(this.bar, this.pipe1.getX(), this.pipe1.getY(),
                (float)this.pipe1.getWidth(), (float)this.pipe1.getHeight());
        this.batcher.draw(this.bar, this.pipe1.getX(), (float)(this.pipe1.getY() + this.pipe1.getHeight() + 40),
                (float)this.pipe1.getWidth(), 50);

        this.batcher.draw(this.bar, this.pipe2.getX(), 0,
                (float)this.pipe2.getWidth(), (float)(this.pipe2.getY() - 40));
        this.batcher.draw(this.bar, this.pipe2.getX(), this.pipe2.getY(),
                (float)this.pipe2.getWidth(), (float)this.pipe2.getHeight());
        this.batcher.draw(this.bar, this.pipe2.getX(), (float)(this.pipe2.getY() + this.pipe2.getHeight() + 40),
                (float)this.pipe2.getWidth(), 50);

        this.batcher.draw(this.bar, this.pipe3.getX(), 0,
                (float)this.pipe3.getWidth(), (float)(this.pipe3.getY() - 40));
        this.batcher.draw(this.bar, this.pipe3.getX(), this.pipe3.getY(),
                (float)this.pipe3.getWidth(), (float)this.pipe3.getHeight());
        this.batcher.draw(this.bar, this.pipe3.getX(), (float)(this.pipe3.getY() + this.pipe3.getHeight() + 40),
                (float)this.pipe3.getWidth(), 50);

    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        this.batcher.begin();
        this.batcher.disableBlending();

        // Draw pipes
        this.drawPipes();
        this.batcher.enableBlending();;

        if (player.shouldntFlap()) {
            batcher.draw(playerMid, player.getX(), player.getY(),
                    player.getWidth() / 2.0f, player.getHeight() / 2.0f,
                    player.getWidth(), player.getHeight(), 1, 1, player.getRotation());

        } else {
            batcher.draw((TextureRegion) playerAnimation.getKeyFrame(runTime), player.getX(),
                    player.getY(), player.getWidth() / 2.0f,
                    player.getHeight() / 2.0f, player.getWidth(), player.getHeight(),
                    1, 1, player.getRotation());
        }

        batcher.end();

    }

    private void initGameObjects() {
        this.player = myWorld.getPlayer();
        this.scroller = myWorld.getScroller();
        this.pipe1 = scroller.getPipe1();
        this.pipe2 = scroller.getPipe2();
        this.pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        playerAnimation = AssetLoader.playerAnimation;
        playerMid = AssetLoader.playerMid;
        playerDown = AssetLoader.playerDown;
        playerUp = AssetLoader.playerUp;
        skullRight = AssetLoader.skullRight;
        skullLeft = AssetLoader.skullLeft;
        bar = AssetLoader.bar;
    }
}
