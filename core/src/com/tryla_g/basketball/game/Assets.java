package com.tryla_g.basketball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

	private static final String TAG = Assets.class.getName();
	
	public static final Assets INSTANCE = new Assets();
	
	private AssetManager assetManager;
	
	public AssetBall ball;
	
	private Assets() {
	}
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);
		
		Texture ballTexture = new Texture(Gdx.files.internal(Constants.TEXTURE_BALL));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		ball = new AssetBall(ballTexture);
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception) throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}
	
	public class AssetBall {
		
		public final TextureRegion ball;
		
		public AssetBall(Texture texture) {
			ball = new TextureRegion(texture, 128, 128);
		}
		
	}
	
}
