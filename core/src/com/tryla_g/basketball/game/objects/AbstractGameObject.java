package com.tryla_g.basketball.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class AbstractGameObject {

	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	
	public Rectangle bounds;
	
	public BodyDef bodyDef;
	public Body body;
	
	public AbstractGameObject() {
		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		scale = new Vector2(1, 1);
		rotation = 0;
		bounds = new Rectangle();
		
		bodyDef = new BodyDef();
	}
	
	public void update(float deltaTime) {
//		position = body.getPosition();
//		rotation = body.getAngle() * MathUtils.radiansToDegrees;
		System.out.println("getAngle():" + body.getAngle());
		System.out.println("Setting position to: " + body.getPosition().x + ", " + body.getPosition().y);
//		body.setTransform(position, body.getAngle());
		
	}
	
	public abstract void render(SpriteBatch batch);
	
}
