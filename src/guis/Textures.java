package guis;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.Loader;

public class Textures {
	
	public static Loader loader = new Loader();
	
	public static GuiTexture inventory_slot_1 = new GuiTexture(loader.loadTexture("inventory_slot"), new Vector2f(-0.3f, -0.85f),
			new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_2 = new GuiTexture(loader.loadTexture("inventory_slot"), new Vector2f(-0.15f, -0.85f),
			new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_3 = new GuiTexture(loader.loadTexture("inventory_slot"), new Vector2f(-0, -0.85f),
			new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_4 = new GuiTexture(loader.loadTexture("inventory_slot"), new Vector2f(0.15f, -0.85f),
			new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_5 = new GuiTexture(loader.loadTexture("inventory_slot"), new Vector2f(0.3f, -0.85f),
			new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_1_current = new GuiTexture(loader.loadTexture("inventory_slot_current"),
			new Vector2f(-0.3f, -0.85f), new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_2_current = new GuiTexture(loader.loadTexture("inventory_slot_current"),
			new Vector2f(-0.15f, -0.85f), new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_3_current = new GuiTexture(loader.loadTexture("inventory_slot_current"),
			new Vector2f(-0, -0.85f), new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_4_current = new GuiTexture(loader.loadTexture("inventory_slot_current"),
			new Vector2f(0.15f, -0.85f), new Vector2f(0.05f, 0.09f));
	
	public static GuiTexture inventory_slot_5_current = new GuiTexture(loader.loadTexture("inventory_slot_current"),
			new Vector2f(0.3f, -0.85f), new Vector2f(0.05f, 0.09f));
	
}
