package com.tryla_g.basketball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldController extends InputAdapter {

	private static final String TAG = WorldController.class.getName();

	public GameWorld gameWorld;

	private boolean ballTouched;
	private float touchX, touchY, releaseX, releaseY;

	public WorldController(PhysicsController physicsController) {
		init(physicsController);
	}

	private void init(PhysicsController physicsController) {
		Gdx.input.setInputProcessor(this);

		gameWorld = new GameWorld(physicsController.world);

		ballTouched = false;
		touchX = 0.0f;
		touchY = 0.0f;
		releaseX = 0.0f;
		releaseY = 0.0f;
	}

	public void update(float deltaTime) {
		gameWorld.update(deltaTime);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.debug(TAG, "touch down: " + screenX + ", " + screenY);
		float ballX = gameWorld.ball.body.getPosition().x;
		float ballY = gameWorld.ball.body.getPosition().y;

		Vector3 vector = new Vector3();
		WorldRenderer.camera.unproject(vector.set(screenX, screenY, 0));

		if ((vector.x > ballX - 20.f && vector.x < ballX + 20.f)
				&& (vector.y > ballY - 20.f && vector.y < ballY + 20.f)) {
			Gdx.app.debug(TAG, "Ball touched");
			ballTouched = true;
			touchX = vector.x;
			touchY = vector.y;
		} else {
			ballTouched = false;
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Gdx.app.debug(TAG, "dragging: " + screenX + ", " + screenY);
		if (ballTouched) {
			Vector3 vector = new Vector3();
			WorldRenderer.camera.unproject(vector.set(screenX, screenY, 0));
			releaseX = vector.x;
			releaseY = vector.y;
			gameWorld.ball.body.setTransform(vector.x, vector.y, gameWorld.ball.body.getAngle());
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (ballTouched) {
			Gdx.app.debug(TAG, "releasing ball");

			Vector3 vector = new Vector3();
			float diffX = 0.0f;
			if (touchX < vector.x) {
				diffX = -(vector.x - touchX);
			} else {
				diffX = touchX - vector.x;
			}
			
			float diffY = 0.0f;
			if (touchY < vector.y) {
				diffY = -(vector.y - touchY);
			} else {
				diffY = touchY - vector.y;
			}
			Gdx.app.debug(TAG, "diff: " + diffX + ", " + diffY);

			boolean negativeX = diffX > 0;
			boolean negativeY = diffY > 0;
			float modifierX = 0.0f;
			float modifierY = 0.0f;
			if (negativeX) {
				modifierX = -1.0f;
			} else {
				modifierX = 1.0f;
			}
			
			if (negativeY) {
				modifierY = -1.0f;
			} else {
				modifierY = 1.0f;
			}
			

			gameWorld.ball.body.applyForce(new Vector2((diffX * 1000) * modifierX, (diffY * 1000) * modifierY),
					gameWorld.ball.body.getWorldCenter(), true);
		}
		return false;
	}

}
