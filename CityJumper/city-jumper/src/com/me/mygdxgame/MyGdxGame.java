package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.me.mygdxgame.screens.GameScreen;

public class MyGdxGame extends Game {

	public void create() {
		setScreen(new GameScreen(this));
	}
	
	public void render() {
		super.render();
	}
	
	public void resume() {
		
	}
	
	public void pause() {
		
	}
	
	public void dispose() {
		
	}
	
}
