package com.tryla_g.basketball.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tryla_g.basketball.game.objects.Ball;
import com.tryla_g.basketball.game.objects.Floor;
import com.tryla_g.basketball.game.objects.Hoop;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

public class GameWorld {

	private static final String TAG = GameWorld.class.getName();
	
	public Ball ball;
	public Floor floor;
	public Hoop hoop;
	
	public GameWorld(World world) {
		init(world);
	}
	
	private void init(World world) {
		ball = new Ball();
		ball.dimension.set(1, 1);
		ball.position.set(0, 0);
		
		ball.bodyDef.type = BodyType.DynamicBody;
		ball.bodyDef.position.set(0, 0);
		ball.body = world.createBody(ball.bodyDef);
		
		CircleShape circle = new CircleShape();
		circle.setRadius(0.3f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		
		ball.fixture = ball.body.createFixture(fixtureDef);

		ball.body.setUserData(ball);
		ball.fixture.setUserData(ball);

		circle.dispose();
		
		floor = new Floor();

		floor.bodyDef.position.set(0, -Constants.VIEWPORT_HEIGHT / 2);
		floor.body = world.createBody(floor.bodyDef);
		floor.body.setUserData(floor);
		
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT / 20);
		
		floor.fixture = floor.body.createFixture(polygon, 0.0f);

		polygon.dispose();
		
		hoop = new Hoop();
		hoop.dimension.set(10, 10);
		hoop.position.set(692, 250); 
		
		hoop.bodyDef.position.set(3.22f, 0.56f);
		hoop.body = world.createBody(hoop.bodyDef);
		
		PolygonShape hoopLeftPoly = new PolygonShape();
		hoopLeftPoly.setAsBox(0.022f, 0.08f);
		
		hoop.leftHoop = hoop.body.createFixture(hoopLeftPoly, 0.0f);
		
		hoopLeftPoly.dispose();
		
		hoop.rightHoopBodyDef.position.set(4.09f, 0.58f);
		hoop.rightHoopBody = world.createBody(hoop.rightHoopBodyDef);
		
		PolygonShape hoopRightPoly = new PolygonShape();
		hoopRightPoly.setAsBox(0.1f,  0.08f);
		
		hoop.rightHoop = hoop.rightHoopBody.createFixture(hoopRightPoly, 0.0f);
		
		hoop.body.setUserData(hoop);
		
		hoopRightPoly.dispose();	
	}
	
	public void update(float deltaTime) {
		if (ball != null) {
			ball.update(deltaTime);
			hoop.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch batch) {
		if (ball != null) {
			ball.render(batch);
			hoop.render(batch);
		}
	}
	
}
