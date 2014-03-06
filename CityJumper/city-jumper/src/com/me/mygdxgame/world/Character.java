package com.me.mygdxgame.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Character extends WorldObjects{

	Sprite sprite;
	
	public Character() {
		sprite = new Sprite();
	}
	
	public void render(SpriteBatch b){
		sprite.draw(b);
	}

}
