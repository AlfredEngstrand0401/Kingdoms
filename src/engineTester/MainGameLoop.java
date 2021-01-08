package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Network.Client;
import Network.Network;
import State.State;
import World.ObjectPlacement;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.LivingEntity;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import guis.Inventory;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import terrains.TerrainTexture;
import textures.ModelTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;

public class MainGameLoop {

	public static List<Entity> entities = new ArrayList<Entity>();
	public static List<LivingEntity> livingEntities = new ArrayList<LivingEntity>();
	public static List<GuiTexture> guis = new ArrayList<GuiTexture>();

	public static void main(String[] args) {
		run();
	}

	public static void run() {

		Client client = new Client("localhost", 25325);

		State.setState(State.GAME_MENU);
		DisplayManager.createDisplay();

		Loader loader = new Loader();

		RawModel playerModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel PlayerModel = new TexturedModel(playerModel,
				new ModelTexture(loader.loadTexture("playerTexture")));
		
		RawModel stallTextureModel = OBJLoader.loadObjModel("stall", loader);
		TexturedModel stallModel = new TexturedModel(stallTextureModel, new ModelTexture(loader.loadTexture("stallTexture")));
		
		Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy2"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("grassy2"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassy2"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("grassy2"));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, bTexture, gTexture);

		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("dirt"));

		Terrain terrain = new Terrain(-0.5f, -0.5f, loader, texturePack, blendMap, "heightmap");
		
		Entity stallEntity = new Entity(stallModel, new Vector3f(0, terrain.getHeightOfTerrain(0, 0), 0), 0, 0, 0, 3, null);


		Player player = new Player(PlayerModel, new Vector3f(0, terrain.getHeightOfTerrain(0, 6), 6), 0, 0, 0, 1, 100,
				0, "op", 0);
		
		Camera camera = new Camera(player);

		livingEntities.add(player);
		
		entities.add(stallEntity);

		MasterRenderer renderer = new MasterRenderer();
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		Inventory inventory = new Inventory();
		ObjectPlacement placer = new ObjectPlacement();
		while (!Display.isCloseRequested()) {
			DisplayManager.updateDisplay();
			if (State.isState(State.PLAYING)) {
				player.move(terrain);
				camera.move();
				picker.update();
				renderer.processTerrain(terrain);
				renderer.render(light, camera);
				guiRenderer.render(MainGameLoop.guis);
				inventory.highLightSlot();
				placer.placeObject(picker);
				updatePlayerData(player);
				Network.createPlayers();

				Client.send("\\move:" + player.getName() + ":" + player.getPosition().getX() + ":"
						+ player.getPosition().getY() + ":" + player.getPosition().getZ());
				
				Client.send("\\health:" + player.getName() + ":" + player.getHealth());

				Network.updateData();
				
				for (Entity entity : MainGameLoop.entities) {
					renderer.processEntity(entity);
				}

				for (LivingEntity livingEntity : livingEntities) {
					renderer.processLivingEntity(livingEntity);
				}

				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					State.setState(State.GAME_MENU);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
					State.setState(State.OPTION_MENU);
				}
			}

			if (State.isState(State.GAME_MENU)) {
				picker.update();
				guiRenderer.render(MainGameLoop.guis);
				if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
					State.setState(State.PLAYING);

				}
				if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
					State.setState(State.OPTION_MENU);
				}
			}

			if (State.isState(State.OPTION_MENU)) {
				if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
					State.setState(State.PLAYING);
				}
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_T)) {

		}
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

	public static void updatePlayerData(Player player) {
		Network.name = player.getName();
		Network.x = player.getPosition().getX();
		Network.y = player.getPosition().getY();
		Network.z = player.getPosition().getZ();
		Network.age = player.getAge();
		Network.health = player.getHealth();
	}

}
