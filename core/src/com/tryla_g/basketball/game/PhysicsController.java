package com.tryla_g.basketball.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsController {
	
	private static final String TAG = PhysicsController.class.getName();
	
	public static World world;
	
	private float accumulator = 0;

	private static final float TIME_STEP = 1 / 60f;
	private static final int VELOCITY_ITERATIONS = 8;
	private static final int POSITION_ITERATIONS = 3;
	
	private Box2DDebugRenderer debugRenderer;
	
	public PhysicsController() {
		init();
	}
	
	private void init() {
		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
	}
	
	public void update(float deltaTime) {
		float frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		while (accumulator >= TIME_STEP) {
			world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
			accumulator -= TIME_STEP;
		}
	}
	
	public void render() {
		debugRenderer.render(world, WorldRenderer.camera.combined);
//		world.step(1 / 45f, 6, 2);
	}
	
}
