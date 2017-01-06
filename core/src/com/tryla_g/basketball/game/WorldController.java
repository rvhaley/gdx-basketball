package com.tryla_g.basketball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldController extends InputAdapter {

	private static final String TAG = WorldController.class.getName();

	public GameWorld gameWorld;

	private boolean ballTouched;
	private Vector2 lastTouch = new Vector2();
	private Vector2 delta = new Vector2();

	public WorldController(PhysicsController physicsController) {
		init(physicsController);
	}

	private void init(PhysicsController physicsController) {
		Gdx.input.setInputProcessor(this);

		gameWorld = new GameWorld(physicsController.world);

		ballTouched = false;
	}

	public void update(float deltaTime) {
		gameWorld.update(deltaTime);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.debug(TAG, "touch down: " + screenX + ", " + screenY);
		Vector3 vector = new Vector3();
		WorldRenderer.camera.unproject(vector.set(screenX, screenY, 0));
		
		float ballX = gameWorld.ball.body.getPosition().x;
		float ballY = gameWorld.ball.body.getPosition().y;
		
		if ((vector.x > ballX - 20.f && vector.x < ballX + 20.f)
				&& (vector.y > ballY - 20.f && vector.y < ballY + 20.f)) {
			Gdx.app.debug(TAG, "Ball touched");
			ballTouched = true;
		} else {
			ballTouched = false;
		}
		
		lastTouch.set(vector.x, vector.y);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 vector = new Vector3();
		WorldRenderer.camera.unproject(vector.set(screenX, screenY, 0));
		
		if (ballTouched) {
			gameWorld.ball.body.setTransform(vector.x,  vector.y, gameWorld.ball.body.getAngle());
		}
		
		Vector2 newTouch = new Vector2(vector.x, vector.y);
		delta = newTouch.cpy().sub(lastTouch);
		lastTouch = newTouch;
		
		Gdx.app.debug(TAG, "Drag delta: " + delta.x + ", " + delta.y);
		
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (ballTouched) {
			Gdx.app.debug(TAG, "releasing ball");
			gameWorld.ball.body.applyForce(new Vector2(delta.x * 20000, delta.y * 20000),
					gameWorld.ball.body.getWorldCenter(), true);
		}
		return false;
	}

}
