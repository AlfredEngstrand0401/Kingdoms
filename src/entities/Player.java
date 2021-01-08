package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends LivingEntity {

	private static final float WALK_SPEED = 20;
	private static final float RUN_SPEED = 35;
	private static final float TURN_SPEED = 120;
	private static final float GRAVITY = -80; 
	private static final float JUMP_POWER = 300;

	private static final float TERRAIN_HEIGHT = -40;

	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	public int health;
	
	public int id;

	private static boolean isInAir = false;

	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, int health,
			int age, String name, int id) {
		super(model, position, rotX, rotY, rotZ, scale, health, age, name);
	}

	
	public void move(Terrain terrain) {
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeInSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeInSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeInSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeInSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().getX(), super.getPosition().getZ());
		if (super.getPosition().y < terrainHeight) {
			upwardsSpeed = 0;
			super.getPosition().y = terrainHeight;
			isInAir = false;
		}
	}

	private void jump() {
		this.upwardsSpeed = JUMP_POWER;
		isInAir = true;
	}

	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			this.currentSpeed = RUN_SPEED;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = WALK_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -WALK_SPEED;
		} else {
			this.currentSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPEED;
		} else {
			this.currentTurnSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (!isInAir) {
				jump();
			}
		}
	}
}
