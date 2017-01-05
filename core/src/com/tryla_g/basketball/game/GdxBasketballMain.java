package com.tryla_g.basketball.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class GdxBasketballMain implements ApplicationListener {

	private static final String TAG = GdxBasketballMain.class.getName();
	
	private WorldController worldController;
	private PhysicsController physicsController;
	private WorldRenderer worldRenderer;
	
	private boolean paused;
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		Assets.INSTANCE.init(new AssetManager());
		
		physicsController = new PhysicsController();
		worldController = new WorldController(physicsController);
		worldRenderer = new WorldRenderer(worldController);
		
		paused = false;
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void render() {
		if (!paused) {
			worldController.update(Gdx.graphics.getDeltaTime());
			physicsController.update(Gdx.graphics.getDeltaTime());
		}
		
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRenderer.render();
		physicsController.render();
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
		Assets.INSTANCE.dispose();
	}

}
