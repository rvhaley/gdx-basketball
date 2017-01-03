package com.tryla_g.basketball.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsController {
	
	private static final String TAG = PhysicsController.class.getName();
	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	
	public PhysicsController() {
		init();
	}
	
	private void init() {
		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
	}
	
	public void update(float deltaTime) {
		
	}
	
	public void render() {
		debugRenderer.render(world, camera.combined);
		world.step(1 / 45f, 6, 2);
	}
	
}
