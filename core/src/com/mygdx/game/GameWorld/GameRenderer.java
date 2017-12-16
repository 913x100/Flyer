package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameOrganize.AssetLoader;


public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private int midPointY;
    private int gameHeight;

    // Game Objects
    private Player player;

    // Game Asset
    private TextureRegion grass;
    private TextureRegion playerUp, playerMid, playerDown;
    private TextureRegion skullUp, skullDown, bar;
    private Animation playerAnimation;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.myWorld = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(true, 136.0F, (float)gameHeight);
        this.batcher = new SpriteBatch();
        this.batcher.setProjectionMatrix(this.cam.combined);
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(this.cam.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();

        batcher.enableBlending();

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
    }

    private void initAssets() {
        grass = AssetLoader.grass;
        playerAnimation = AssetLoader.playerAnimation;
        playerMid = AssetLoader.playerMid;
        playerDown = AssetLoader.playerDown;
        playerUp = AssetLoader.playerUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
}
