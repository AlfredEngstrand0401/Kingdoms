package World;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import toolbox.MousePicker;

public class ObjectPlacement {
	
	public List<Entity> objects = new ArrayList<Entity>();
	
	public void placeObject(MousePicker picker) {
		Vector3f terrainPoint = picker.getCurrentTerrainPoint();
		if(terrainPoint == null) {
			return;
		}
		for(Entity object : objects) {
		Vector3f position = picker.getCurrentTerrainPoint();
		object.setPosition(position);
		System.out.println("Placed new entity at: " + position);
		}
	}
}
