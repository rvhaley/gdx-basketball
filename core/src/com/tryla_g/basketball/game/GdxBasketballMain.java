package com.tryla_g.basketball.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class GdxBasketballMain implements ApplicationListener {

	private static final String TAG = GdxBasketballMain.class.getName();
	
	private WorldController worldController;
	private PhysicsController physicsController;
	private WorldRenderer worldRenderer;
	
	private boolean paused;
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		worldController = new WorldController();
		physicsController = new PhysicsController();
		worldRenderer = new WorldRenderer(worldController);
		
		paused = false;
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
		if (!paused) {
			worldController.update(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
				
	}

}
