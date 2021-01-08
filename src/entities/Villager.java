package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Villager extends LivingEntity {

	public Villager(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, int health,
			int age, String name) {
		super(model, position, rotX, rotY, rotZ, scale, health, age, name);
	}
	

}
