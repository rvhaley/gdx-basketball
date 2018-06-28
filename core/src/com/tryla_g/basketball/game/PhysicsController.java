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

public class PhysicsController implements ContactListener {
	
	private static final String TAG = PhysicsController.class.getName();
	
	public static World world;

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
