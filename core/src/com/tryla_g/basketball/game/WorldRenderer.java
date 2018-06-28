package com.tryla_g.basketball.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {

	private static final String TAG = WorldRenderer.class.getName();
	
	public static OrthographicCamera camera;
	public static OrthographicCamera backgroundCamera;
	private SpriteBatch batch;
	private WorldController worldController;
	
	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}
	
	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
//		camera.setToOrtho(false);
		camera.update();
		backgroundCamera = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		backgroundCamera.position.set(0, 0, 0);
		backgroundCamera.update();
	}
	
	public void render() {
		batch.setProjectionMatrix(backgroundCamera.combined);
		batch.begin();
		renderAudience(batch);

		renderHoopBackground(batch);
		
		batch.end();
		

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
//		renderHoopBackground(batch);
		
		
		worldController.gameWorld.render(batch);
		batch.setProjectionMatrix(backgroundCamera.combined);
		
		renderHoopForeground(batch);
		batch.end();
	}
	
	private void renderAudience(SpriteBatch batch) {
		batch.draw(Assets.INSTANCE.audience.audience, 0, 0, 800 / 2, 480 / 2, 800, 480, 1, 1, 0);
	}
	
	private void renderHoopBackground(SpriteBatch batch) {
		batch.draw(Assets.INSTANCE.hoop.background, 696, 250, 108 / 2, 66 / 2, 108, 66, 1, 1, 0);
	}
	
	private void renderHoopForeground(SpriteBatch batch) {
		batch.draw(Assets.INSTANCE.hoop.foreground, 696, 250, 108 / 2, 66 / 2, 108, 66, 1, 1, 0);
	}
	
	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
		//backgroundCamera.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		backgroundCamera.viewportWidth = ((Constants.VIEWPORT_GUI_HEIGHT / height) * width);
		backgroundCamera.position.set(backgroundCamera.viewportWidth / 2, backgroundCamera.viewportHeight / 2, 0);
		backgroundCamera.update();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

}
