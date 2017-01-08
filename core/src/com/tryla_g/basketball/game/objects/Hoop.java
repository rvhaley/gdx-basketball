package com.tryla_g.basketball.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.tryla_g.basketball.game.Assets;

public class Hoop extends AbstractGameObject {
	
	private static final String TAG = Hoop.class.getName();

	public Fixture leftHoop, rightHoop;
	public Body rightHoopBody;
	public BodyDef rightHoopBodyDef;

	public TextureRegion backgroundTexture, foregroundTexture;

	public Hoop() {
		init();
	}

	private void init() {
		dimension.set(1, 1);

		backgroundTexture = Assets.INSTANCE.hoop.background;
		foregroundTexture = Assets.INSTANCE.hoop.foreground;

		origin.set(dimension.x / 2, dimension.y / 2);
		bounds.set(0, 0, dimension.x, dimension.y);
		rightHoopBodyDef = new BodyDef();
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(backgroundTexture.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.x, rotation, backgroundTexture.getRegionX(), backgroundTexture.getRegionY(),
				backgroundTexture.getRegionX(), backgroundTexture.getRegionY(), false, false);
		batch.setColor(1, 1, 1, 1);
	}

}
