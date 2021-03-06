package com.me.mygdxgame.screens;

import com.badlogic.gdx.Screen;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.world.World;

public class GameScreen implements Screen{
	
	private MyGdxGame game;
	World world;

	public GameScreen(MyGdxGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		world.update();
		world.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		if(world == null) {
			world = new World();
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
