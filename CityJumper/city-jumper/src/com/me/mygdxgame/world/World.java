package com.me.mygdxgame.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.MyGdxGame;

public class World {
	MyGdxGame game;
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	Physics physics;
	Character character;
	
	public World(MyGdxGame game) {
		this.game = game;
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
		physics = new Physics(camera, this);
		
		createCharacter();
	}
	
	private void createCharacter() {
		
		character = new Character();//starting position on screen
		physics.createCharacterBody(position, character);
	}
	
	public void update() {
		physics.update();
	}
	
	public void render() {
		spriteBatch.begin();
		character.render(spriteBatch);
		spriteBatch.end();
	}
		
}
