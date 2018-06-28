package com.tryla_g.basketball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.tryla_g.basketball.game.objects.Floor;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

public class PhysicsController implements ContactListener {
	
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
		world.setContactListener(this);
		debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
	}
	
	public void update(float deltaTime) {
//		float frameTime = Math.min(deltaTime, 0.25f);
//		accumulator += frameTime;
//		while (accumulator >= TIME_STEP) {
//			world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
//			accumulator -= TIME_STEP;
//		}
	}
	
	public void render() {
		debugRenderer.render(world, WorldRenderer.camera.combined);
		world.step(1 / 45f, 6, 2);
	}

	@Override
	public void beginContact(Contact contact) {
		Gdx.app.debug(TAG, "beginContact()");
		if (contact.getFixtureA().getBody().getUserData() instanceof Floor || contact.getFixtureB().getBody().getUserData() instanceof Floor) {
			Gdx.app.debug(TAG, "beginContact() contact with body having instance of Floor class as user data");
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
}
