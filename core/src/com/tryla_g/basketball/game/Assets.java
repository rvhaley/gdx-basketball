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
	
	public AssetAudience audience;
	public AssetBall ball;
	public AssetHoop hoop;
	
	private Assets() {
	}
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);
		
		Texture audienceTexure = new Texture(Gdx.files.internal(Constants.TEXTURE_AUDIENCE));
		audienceTexure.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		audience = new AssetAudience(audienceTexure);
		
		Texture ballTexture = new Texture(Gdx.files.internal(Constants.TEXTURE_BALL));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		ball = new AssetBall(ballTexture);
		
		Texture hoopBackgroundTexture = new Texture(Gdx.files.internal(Constants.TEXTURE_HOOP));
		hoopBackgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture hoopForegroundTexture = new Texture(Gdx.files.internal(Constants.TEXTURE_HOOP_FOREGROUND));
		hoopForegroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		hoop = new AssetHoop(hoopBackgroundTexture, hoopForegroundTexture);
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception) throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}
	
	public class AssetAudience {
		
		public final TextureRegion audience;
		
		public AssetAudience(Texture texture) {
			audience = new TextureRegion(texture, 800, 480);
		}
		
	}
	
	public class AssetBall {
		
		public final TextureRegion ball;
		
		public AssetBall(Texture texture) {
			ball = new TextureRegion(texture, 128, 128);
		}
		
	}
	
	public class AssetHoop {
		
		public final TextureRegion background, foreground;
		
		public AssetHoop(Texture textureBackground, Texture textureForeground) {
			background = new TextureRegion(textureBackground, 108, 66);
			foreground = new TextureRegion(textureForeground, 108, 66);
		}
	}
	
}
