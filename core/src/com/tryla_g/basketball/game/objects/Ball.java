package com.tryla_g.basketball.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.tryla_g.basketball.game.Assets;
import com.tryla_g.basketball.game.PhysicsController;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

public class Ball extends AbstractGameObject {

	public Fixture fixture;

	public TextureRegion regionBall;

	public Ball() {
		init();
	}

	private void init() {
		dimension.set(1, 1);
		regionBall = Assets.INSTANCE.ball.ball;
		origin.set(dimension.x / 2, dimension.y / 2);
		bounds.set(0, 0, dimension.x, dimension.y);
	}

	@Override
	public void render(SpriteBatch batch) {
		Box2DSprite.draw(batch, PhysicsController.world);
//		batch.draw(regionBall.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y,
//				scale.x, scale.y, rotation, regionBall.getRegionX(), regionBall.getRegionY(),
//				regionBall.getRegionWidth(), regionBall.getRegionHeight(), false, false);
		batch.setColor(1, 1, 1, 1);
	}

}
