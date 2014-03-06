package com.me.mygdxgame.world;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Noah Butler
 * 
 *         This class will be extended by all world objects such as ground,
 *         character, and builds. It is needed so that the the collision dection
 *         in box2d can cast to a common object
 * 
 */
public class WorldObjects {
	private Vector2 position;
	private int type;

	public WorldObjects() {
		position = new Vector2();
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return this.position;
	}
	
	public int getType() {
		return this.type;
	}
	
}
