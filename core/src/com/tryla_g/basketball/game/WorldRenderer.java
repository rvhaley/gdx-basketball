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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderAudience(batch);
		renderHoopBackground(batch);
		worldController.gameWorld.render(batch);
		renderHoopForeground(batch);
		batch.end();
	}
	
	private void renderAudience(SpriteBatch batch) {
		batch.draw(Assets.INSTANCE.audience.audience, -400, -240, 800 / 2, 480 / 2, 800, 480, 1, 1, 0);
	}
	
	private void renderHoopBackground(SpriteBatch batch) {
		batch.draw(Assets.INSTANCE.hoop.background, 296, 40, 82 / 2, 50 / 2, 82, 50, 1, 1, 0);
	}
	
	private void renderHoopForeground(SpriteBatch batch) {
		batch.draw(Assets.INSTANCE.hoop.foreground, 296, 40, 82 / 2, 50 / 2, 82, 50, 1, 1, 0);
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
