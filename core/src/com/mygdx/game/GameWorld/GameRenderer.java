package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.GameObjects.Pipe;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.ScrollHandler;
import com.mygdx.game.GameOrganize.AssetLoader;


public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera cam;
    private SpriteBatch batcher;

    private ShapeRenderer shapeRenderer;
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
        this.world = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136.0F, (float)gameHeight);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(this.cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(this.cam.combined);

        initGameObjects();
        initAssets();
    }

    private void drawPipes() {
        batcher.draw(bar, pipe1.getX(), 0,
                (float)pipe1.getWidth(), (float)(pipe1.getY() - 40));
        batcher.draw(bar, pipe1.getX(), pipe1.getY(),
                (float)pipe1.getWidth(), (float)pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), (float)(pipe1.getY() + pipe1.getHeight() + 40),
                (float)pipe1.getWidth(), 50);

        batcher.draw(bar, pipe2.getX(), 0,
                (float)pipe2.getWidth(), (float)(pipe2.getY() - 40));
        batcher.draw(bar, pipe2.getX(), pipe2.getY(),
                (float)pipe2.getWidth(), (float)pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), (float)(pipe2.getY() + pipe2.getHeight() + 40),
                (float)pipe2.getWidth(), 50);

        batcher.draw(bar, pipe3.getX(), 0,
                (float)pipe3.getWidth(), (float)(pipe3.getY() - 40));
        batcher.draw(bar, pipe3.getX(), pipe3.getY(),
                (float)pipe3.getWidth(), (float)pipe3.getHeight());
        this.batcher.draw(bar, pipe3.getX(), (float)(pipe3.getY() + pipe3.getHeight() + 40),
                (float)pipe3.getWidth(), 50);

    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();

        // Draw pipes
        drawPipes();
        batcher.enableBlending();;

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



        /*
        // Bird hitbox
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(player.getHitBox().x,
                player.getHitBox().y, player.getHitBox().radius);
        // Bar up for pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getPipeMid().x, pipe1.getPipeMid().y,
                pipe1.getPipeMid().width, pipe1.getPipeMid().height);
        shapeRenderer.rect(pipe2.getPipeMid().x, pipe2.getPipeMid().y,
                pipe2.getPipeMid().width, pipe2.getPipeMid().height);
        shapeRenderer.rect(pipe3.getPipeMid().x, pipe3.getPipeMid().y,
                pipe3.getPipeMid().width, pipe3.getPipeMid().height);

        shapeRenderer.end();
        */
        String score = String.valueOf(world.getScore());

        AssetLoader.font.draw(batcher, "" + world.getScore(), (136 / 2)
                - (3 * score.length() - 1), 11);

        AssetLoader.problem.draw(batcher, "" + pipe1.getProblem(), pipe1.getX(), 150);
        AssetLoader.problem.draw(batcher, "" + pipe2.getProblem(), pipe2.getX(), 150);
        AssetLoader.problem.draw(batcher, "" + pipe3.getProblem(), pipe3.getX(), 150);

        AssetLoader.problem.draw(batcher, "" + pipe1.getAnsUp(), pipe1.getX(), pipe1.getY() - 20);
        AssetLoader.problem.draw(batcher, "" + pipe1.getAnsDown(), pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 20);
        AssetLoader.problem.draw(batcher, "" + pipe2.getAnsUp(), pipe2.getX(), pipe2.getY() - 20);
        AssetLoader.problem.draw(batcher, "" + pipe2.getAnsDown(), pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 20);
        AssetLoader.problem.draw(batcher, "" + pipe3.getAnsUp(), pipe3.getX(), pipe3.getY() - 20);
        AssetLoader.problem.draw(batcher, "" + pipe3.getAnsDown(), pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 20);
        //AssetLoader.problem.draw(batcher, "" + pipe2.getProblem(), pipe2.getX(), 150);
        //AssetLoader.problem.draw(batcher, "" + pipe3.getProblem(), pipe3.getX(), 150);

        batcher.end();

    }

    private void initGameObjects() {
        this.player = world.getPlayer();
        this.scroller = world.getScroller();
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
