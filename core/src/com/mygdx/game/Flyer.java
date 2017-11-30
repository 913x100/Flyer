package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameOrganize.AssetLoader;
import com.mygdx.game.Screens.GameScreen;

public class Flyer extends Game {

	@Override
	public void create () {
		Gdx.app.log("SuperFly", "created");
		AssetLoader.load();
		this.setScreen(new GameScreen());
	}
}
