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
import com.mygdx.game.GameObjects.Grass;
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
    private TextureRegion playerUp, playerMid, playerDown;
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
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        // Pipe 1
        shapeRenderer.rect(pipe1.getX(), 0, (float)pipe1.getWidth(), (float)(pipe1.getY() - 40));
        shapeRenderer.rect(pipe1.getX(), pipe1.getY(), (float)pipe1.getWidth(), (float)pipe1.getHeight());
        shapeRenderer.rect(pipe1.getX(), (float)(pipe1.getY() + pipe1.getHeight() + 40), (float)pipe1.getWidth(), 50);

        // Pipe 2
        shapeRenderer.rect(pipe2.getX(), 0, (float)pipe2.getWidth(), (float)(pipe2.getY() - 40));
        shapeRenderer.rect(pipe2.getX(), pipe2.getY(), (float)pipe2.getWidth(), (float)pipe2.getHeight());
        shapeRenderer.rect(pipe2.getX(), (float)(pipe2.getY() + pipe2.getHeight() + 40), (float)pipe2.getWidth(), 50);

        // Pipe 3
        shapeRenderer.rect(pipe3.getX(), 0, (float)pipe3.getWidth(), (float)(pipe3.getY() - 40));
        shapeRenderer.rect(pipe3.getX(), pipe3.getY(), (float)pipe3.getWidth(), (float)pipe3.getHeight());
        shapeRenderer.rect(pipe3.getX(), (float)(pipe3.getY() + pipe3.getHeight() + 40), (float)pipe3.getWidth(), 50);


        // Bar1
        shapeRenderer.rect(pipe1.getBarUp().x, pipe1.getBarUp().y, (float)pipe1.getBarUp().width, (float)pipe1.getBarUp().height);
        shapeRenderer.rect(pipe1.getBarMid1().x, pipe1.getBarMid1().y, (float)pipe1.getBarMid1().width, (float)pipe1.getBarMid1().height);
        shapeRenderer.rect(pipe1.getBarMid2().x, pipe1.getBarMid2().y, (float)pipe1.getBarMid2().width, (float)pipe1.getBarMid2().height);
        shapeRenderer.rect(pipe1.getBarDown().x, pipe1.getBarDown().y, (float)pipe1.getBarDown().width, (float)pipe1.getBarDown().height);

        // Bar2
        shapeRenderer.rect(pipe2.getBarUp().x, pipe2.getBarUp().y, (float)pipe2.getBarUp().width, (float)pipe2.getBarUp().height);
        shapeRenderer.rect(pipe2.getBarMid1().x, pipe2.getBarMid1().y, (float)pipe2.getBarMid1().width, (float)pipe2.getBarMid1().height);
        shapeRenderer.rect(pipe2.getBarMid2().x, pipe2.getBarMid2().y, (float)pipe2.getBarMid2().width, (float)pipe2.getBarMid2().height);
        shapeRenderer.rect(pipe2.getBarDown().x, pipe2.getBarDown().y, (float)pipe2.getBarDown().width, (float)pipe2.getBarDown().height);

        // Bar3
        shapeRenderer.rect(pipe3.getBarUp().x, pipe3.getBarUp().y, (float)pipe3.getBarUp().width, (float)pipe3.getBarUp().height);
        shapeRenderer.rect(pipe3.getBarMid1().x, pipe3.getBarMid1().y, (float)pipe3.getBarMid1().width, (float)pipe3.getBarMid1().height);
        shapeRenderer.rect(pipe3.getBarMid2().x, pipe3.getBarMid2().y, (float)pipe3.getBarMid2().width, (float)pipe3.getBarMid2().height);
        shapeRenderer.rect(pipe3.getBarDown().x, pipe3.getBarDown().y, (float)pipe3.getBarDown().width, (float)pipe3.getBarDown().height);

        shapeRenderer.end();
    }

    private void drawGrass() {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(214 / 255.0f, 250 / 255.0f, 63 / 255.0f, 1);
        shapeRenderer.rect(0.0F, (float)(this.midPointY + 66), 136.0F, 11.0F);
        shapeRenderer.setColor(0.5764706F, 0.3137255F, 0.105882354F, 1.0F);
        shapeRenderer.rect(0.0F, (float)(this.midPointY + 77), 136.0F, 52.0F);
        shapeRenderer.end();
    }

    private void drawBackground() {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
        shapeRenderer.rect(0.0F, 0.0F, 136.0F, (float)(this.midPointY + 66));
        shapeRenderer.end();
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw pipes
        drawBackground();
        drawPipes();
        drawGrass();

        batcher.begin();
        batcher.disableBlending();

        batcher.enableBlending();;

        if (player.shouldntFlap()) {
            batcher.draw(playerMid, player.getX(), player.getY(), player.getWidth(), player.getHeight());

        } else {
            batcher.draw((TextureRegion) playerAnimation.getKeyFrame(runTime), player.getX(),
                    player.getY(), player.getWidth(), player.getHeight());
        }

        if(world.isReady()) {
            //AssetLoader.font.draw(batcher, "Touch me", (136 / 2) - (42 - 1), 75);
        } else {
            if(world.isGameOver()) {
                AssetLoader.font.draw(batcher, "Game Over", 24, 55);
            }
            String score = String.valueOf(world.getScore());

            AssetLoader.font.draw(batcher, "" + world.getScore(), (136 / 2) - (3 * score.length() - 1), 11);

            AssetLoader.problem.draw(batcher, "" + pipe1.getProblem(), pipe1.getX(), midPointY + 70);
            AssetLoader.problem.draw(batcher, "" + pipe2.getProblem(), pipe2.getX(), midPointY + 70);
            AssetLoader.problem.draw(batcher, "" + pipe3.getProblem(), pipe3.getX(), midPointY + 70);

            AssetLoader.problem.draw(batcher, "" + pipe1.getAnsUp(), pipe1.getX() + 2, pipe1.getY() - 22);
            AssetLoader.problem.draw(batcher, "" + pipe1.getAnsDown(), pipe1.getX() + 2, pipe1.getY() + pipe1.getHeight() + 18);
            AssetLoader.problem.draw(batcher, "" + pipe2.getAnsUp(), pipe2.getX() + 2, pipe2.getY() - 22);
            AssetLoader.problem.draw(batcher, "" + pipe2.getAnsDown(), pipe2.getX() + 2, pipe2.getY() + pipe2.getHeight() + 18);
            AssetLoader.problem.draw(batcher, "" + pipe3.getAnsUp(), pipe3.getX()+ 2, pipe3.getY() - 22);
            AssetLoader.problem.draw(batcher, "" + pipe3.getAnsDown(), pipe3.getX()+ 2, pipe3.getY() + pipe3.getHeight() + 18);

        }
        batcher.end();

    }

    private void initGameObjects() {
        player = world.getPlayer();
        scroller = world.getScroller();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        playerAnimation = AssetLoader.playerAnimation;
        playerMid = AssetLoader.playerMid;
        playerDown = AssetLoader.playerDown;
        playerUp = AssetLoader.playerUp;
    }
}
