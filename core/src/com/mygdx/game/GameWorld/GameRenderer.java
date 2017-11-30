package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    }

    public void render(float runTime) {
        Player bird = this.myWorld.getPlayer();
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
        this.shapeRenderer.rect(0.0F, 0.0F, 136.0F, (float)(this.midPointY + 66));
        this.shapeRenderer.setColor(0.43529412F, 0.7294118F, 0.1764706F, 1.0F);
        this.shapeRenderer.rect(0.0F, (float)(this.midPointY + 66), 136.0F, 11.0F);
        this.shapeRenderer.setColor(0.5764706F, 0.3137255F, 0.105882354F, 1.0F);
        this.shapeRenderer.rect(0.0F, (float)(this.midPointY + 77), 136.0F, 52.0F);
        this.shapeRenderer.end();
        this.batcher.begin();
        this.batcher.disableBlending();
        this.batcher.draw(AssetLoader.bg, 0.0F, (float)(this.midPointY + 23), 136.0F, 43.0F);
        this.batcher.enableBlending();
        this.batcher.draw((Texture) AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        this.batcher.end();
    }
}
