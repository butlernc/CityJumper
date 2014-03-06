package com.me.mygdxgame.world;

import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Physics {
	private static final float SCALING_FACTOR = .05f;
	private static final float BOX_STEP = 1/60f;
	private static final int  BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;
	private float accumulator;
	
	private com.me.mygdxgame.world.World world;
	private OrthographicCamera camera;
	
	private World physicsWorld;
	private Vector2 gravity;
	private Box2DDebugRenderer debug;
	
	Array<Body> buildingBodies;
	Array<Body> deletableBodies;
	Array<Body> groundBodies;
	
	Iterator<Body> bi;
	Body characterBody;
	
	public Physics(OrthographicCamera camera, com.me.mygdxgame.world.World world) {
		this.camera = camera;
		this.world = world;
		
		createWorld();
	}
	
	private void createWorld() {
		gravity = new Vector2();
		physicsWorld = new World(gravity, false);
		debug = new Box2DDebugRenderer();
		
		buildingBodies  = new Array<Body>();
		deletableBodies = new Array<Body>();
		groundBodies    = new Array<Body>();
		
		createCollisionListener();
	}
	
	public void update(float delta) {
		
		updateObjects();
		
		accumulator += delta;
		
		while(accumulator > BOX_STEP) {
			physicsWorld.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
			accumulator -= BOX_STEP;
		}
		
		//safe to remove
		removeDeadBodies();
		//so I can see the bodies
		debug.render(physicsWorld, camera.combined);
	}
	
	private void createCollisionListener() {
		physicsWorld.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				
				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureA().getBody();
				WorldObjects worldObject_a = (WorldObjects) bodyA.getUserData();
				WorldObjects worldObject_b = (WorldObjects) bodyB.getUserData();
				
				
			}
		});
	}
	
	public void createCharacterBody(Vector2 position, Character character) {
		
		//make the shape of the physic's body
		PolygonShape characterShape = new PolygonShape();
		characterShape.setAsBox(.2f, 1.0f); //each number is half of actual size
		
		//make a body to add to the world
		BodyDef characterBodyDef = new BodyDef();
		characterBodyDef.type = BodyType.KinematicBody;
		characterBodyDef.position.set(position);
		
		//add bodydef to a world body
		characterBody = physicsWorld.createBody(characterBodyDef);
		
		//make a fixture for the body and shape to it
		FixtureDef characterFixture = new FixtureDef();
		characterFixture.shape = characterShape;
		characterFixture.isSensor = false;
		
		//add fixture to the world body
		characterBody.createFixture(characterFixture);
		
		//add body's userdata
		characterBody.setUserData(character);
		
		//delete uneeded shape
		characterShape.dispose();
	}
	
	private void updateObjects() {
		Array<Body> bodies = new Array<Body>();
		physicsWorld.getBodies(bodies);

		for(int i = 0; i < bodies.size; i++) {
			Body b = bodies.get(i);
			
			WorldObjects object = (WorldObjects) b.getUserData();
			
			if(object != null) {
				object.setPosition(b.getPosition());
			}
		}
	}
	
	private void removeBodySafely(Body body) {

		//gets the body's joints
		final Array<JointEdge> list = body.getJointList();
		
		//goes through each one and destroys it
		while(list.size > 0) {
			physicsWorld.destroyJoint(list.get(0).joint);
		}
		
		//destroy child object
		body.setUserData(null);
		//destroy the body
		physicsWorld.destroyBody(body);
	}
	
	private void removeDeadBodies() {
		
		for(int i = 0; i < deletableBodies.size; i++) {
			
			Body body = deletableBodies.get(i);
			
			if(!physicsWorld.isLocked() && body != null) {
				//removes the body from the array
				deletableBodies.removeIndex(i);
				//safely removes body from physics world
				removeBodySafely(body);
			}
		}
	}

}
