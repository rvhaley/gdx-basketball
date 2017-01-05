package com.tryla_g.basketball.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tryla_g.basketball.game.objects.Ball;
import com.tryla_g.basketball.game.objects.Floor;

public class GameWorld {

	private static final String TAG = GameWorld.class.getName();
	
	public Ball ball;
	public Floor floor;
	
	public GameWorld(World world) {
		init(world);
	}
	
	private void init(World world) {
		ball = new Ball();
		
		ball.bodyDef.type = BodyType.DynamicBody;
		ball.bodyDef.position.set(100, 300);
		ball.body = world.createBody(ball.bodyDef);
		
		CircleShape circle = new CircleShape();
		circle.setRadius(20.0f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		
		ball.fixture = ball.body.createFixture(fixtureDef);
		
		circle.dispose();
		
		floor = new Floor();
		
		floor.bodyDef.position.set(0, 10);
		floor.body = world.createBody(floor.bodyDef);
		
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(Constants.VIEWPORT_WIDTH, 10.0f);
		
		floor.fixture = floor.body.createFixture(polygon, 0.0f);

		polygon.dispose();
	}
	
	public void update(float deltaTime) {
		if (ball != null) {
			ball.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch batch) {
		if (ball != null) {
			ball.render(batch);
		}
	}
	
}
