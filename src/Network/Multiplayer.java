package Network;

import java.util.ArrayList;

public class Multiplayer {
	
	private String name;
	private float x;
	private float y;
	private float z;
	private int age;
	private int health;
	
	public static ArrayList<Multiplayer> multiplayers = new ArrayList<Multiplayer>();
	
	public Multiplayer(String name, float x, float y, float z, int age, int health) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.age = age;
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public static void createMultiplayer(String name, float x, float y, float z, int age, int health) {
		Multiplayer multiplayer = new Multiplayer(name, x, y, z, age, health);
		multiplayers.add(multiplayer);
	}

}
