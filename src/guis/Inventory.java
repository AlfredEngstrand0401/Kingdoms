package guis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import engineTester.MainGameLoop;
import entities.Entity;
import renderEngine.Loader;
import textures.ModelTexture;

public class Inventory {

	public Map<Integer, Entity> entity_slots = new HashMap<Integer, Entity>();
	List<Integer> takenSlots = new ArrayList<Integer>();
	private int currentSlot;
	private int totalSlots;

	public int getCurrentSlot() {
		return currentSlot;
	}

	public void setCurrentSlot(int slot) {
		this.currentSlot = slot;
	}

	public int getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(int slots) {
		this.totalSlots = slots;
	}

	public void highLightSlot() {
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			if (currentSlot != 1) {
				setCurrentSlot(1);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
			if (currentSlot != 2) {
				setCurrentSlot(2);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
			if (currentSlot != 3) {
				setCurrentSlot(3);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
			if (currentSlot != 4) {
				setCurrentSlot(4);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
			if (currentSlot != 5) {
				setCurrentSlot(5);
			}
		}
	}

	public Entity getEntity(int slot) {
		return entity_slots.get(slot);
	}

	public void putEntityInSlot(int slot, Entity entity) {
		entity_slots.put(slot, entity);
	}

	public void setSlotIcon(Loader loader, int slot, String texture) {

		if (slot == 1) {

			GuiTexture icon = new GuiTexture(loader.loadTexture(texture), new Vector2f(-0.3f, -0.85f),
					new Vector2f(0.03f, 0.06f));
			MainGameLoop.guis.add(icon);

		}

		if (slot == 2) {

			GuiTexture icon = new GuiTexture(loader.loadTexture(texture), new Vector2f(-0.15f, -0.85f),
					new Vector2f(0.03f, 0.06f));
			MainGameLoop.guis.add(icon);
		}

		if (slot == 3) {

			GuiTexture icon = new GuiTexture(loader.loadTexture(texture), new Vector2f(-0, -0.85f),
					new Vector2f(0.03f, 0.06f));
			MainGameLoop.guis.add(icon);
		}

		if (slot == 4) {

			GuiTexture icon = new GuiTexture(loader.loadTexture(texture), new Vector2f(0.15f, -0.85f),
					new Vector2f(0.03f, 0.06f));
			MainGameLoop.guis.add(icon);

		}

		if (slot == 5) {

			GuiTexture icon = new GuiTexture(loader.loadTexture(texture), new Vector2f(0.3f, -0.85f),
					new Vector2f(0.03f, 0.06f));
			MainGameLoop.guis.add(icon);

		}
	}
}
