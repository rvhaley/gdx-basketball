package com.tryla_g.basketball.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {

	private static final String TAG = WorldRenderer.class.getName();
	
	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;
	
	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}
	
	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(800, 480);
		camera.position.set(0, 0, 0);
		camera.update();
	}
	
	public void render() {
		renderWorld(batch);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		worldController.gameWorld.render(batch);
		batch.end();
	}
	
	private void renderWorld(SpriteBatch batch) {
		
	}
	
	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

}
