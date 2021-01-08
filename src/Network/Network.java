package Network;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.LivingEntity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class Network {

	public static String name = "op";
	public static float x;
	public static float y;
	public static float z;
	public static int age;
	public static int health;
	public static boolean generate;

	public static ArrayList<LivingEntity> players = new ArrayList<LivingEntity>();

	public static void createPlayers() {

		if (generate == true) {

			for (int i = 0; i < Multiplayer.multiplayers.size(); i++) {

				String name = Multiplayer.multiplayers.get(i).getName();
				float x = Multiplayer.multiplayers.get(i).getX();
				float y = Multiplayer.multiplayers.get(i).getY();
				float z = Multiplayer.multiplayers.get(i).getZ();
				int age = Multiplayer.multiplayers.get(i).getAge();
				int health = Multiplayer.multiplayers.get(i).getHealth();

				if (players.size() < Multiplayer.multiplayers.size()) {
					Loader loader = new Loader();

					RawModel playerModel = OBJLoader.loadObjModel("person", loader);
					TexturedModel PlayerModel = new TexturedModel(playerModel,
							new ModelTexture(loader.loadTexture("playerTexture")));

					LivingEntity player = new LivingEntity(PlayerModel, new Vector3f(x, y, z), 0, 0, 0, 1, health, age,
							name);
					MainGameLoop.livingEntities.add(player);
					players.add(player);

					generate = false;
				}
			}
		}
	}

	public static void updateData() {

		for (int i = 0; i < Multiplayer.multiplayers.size(); i++) {

			Multiplayer multiplayer = Multiplayer.multiplayers.get(i);

			String data = Client.playerdata.get(multiplayer);

			if (data != null) {

				String name = multiplayer.getName();

				LivingEntity player = players.get(i);

				if (player.getName().equals(name)) {

					String[] parts = data.split(":");

					float x = Float.parseFloat(parts[1]);
					float y = Float.parseFloat(parts[2]);
					float z = Float.parseFloat(parts[3]);

					player.setPosition(new Vector3f(x, y, z));

				}
			}
		}
	}
}
