package com.tryla_g.basketball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {

	private static final String TAG = WorldController.class.getName();
	
	// Camera Helper
	
	public WorldController() {
		init();
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
	}
	
	public void update(float deltaTime) {
		
	}
	
}
