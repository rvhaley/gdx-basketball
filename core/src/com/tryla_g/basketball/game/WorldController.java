package com.tryla_g.basketball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {

	private static final String TAG = WorldController.class.getName();
	
	private GameWorld gameWorld;
	
	public WorldController(PhysicsController physicsController) {
		init(physicsController);
	}
	
	private void init(PhysicsController physicsController) {
		Gdx.input.setInputProcessor(this);
		
		gameWorld = new GameWorld(physicsController.world);
	}
	
	public void update(float deltaTime) {
		gameWorld.update(deltaTime);
	}
	
}
