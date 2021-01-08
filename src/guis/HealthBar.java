package guis;

import org.lwjgl.util.vector.Vector2f;

import engineTester.MainGameLoop;
import entities.Player;
import renderEngine.Loader;

public class HealthBar {

	public int getHealth(Player player) {
		return player.getHealth();
	}

	public void setHealthImage(String texture, Player player, Loader loader) {

		if (player.getHealth() == 100) {
			
			GuiTexture healthbar = new GuiTexture(loader.loadTexture("health_bar"), new Vector2f(-0.05f, 0.9f),
					new Vector2f(0.5f, 0.5f));
			MainGameLoop.guis.add(healthbar);
		}

		if (player.getHealth() < 100 && player.getHealth() > 90) {

		}

		if (player.getHealth() == 90) {

		}

		if (player.getHealth() < 90 && player.getHealth() > 80) {

		}

		if (player.getHealth() == 80) {

		}

		if (player.getHealth() < 80 && player.getHealth() > 70) {

		}

		if (player.getHealth() == 70) {

		}

		if (player.getHealth() < 70 && player.getHealth() > 60) {

		}

		if (player.getHealth() == 60) {

		}

		if (player.getHealth() < 60 && player.getHealth() > 50) {

		}

		if (player.getHealth() == 50) {

		}

		if (player.getHealth() < 50 && player.getHealth() > 40) {

		}

		if (player.getHealth() == 40) {

		}

		if (player.getHealth() < 40 && player.getHealth() > 30) {

		}

		if (player.getHealth() == 30) {

		}

		if (player.getHealth() < 30 && player.getHealth() > 20) {

		}

		if (player.getHealth() == 20) {

		}

		if (player.getHealth() < 20 && player.getHealth() > 10) {

		}

		if (player.getHealth() == 10) {

		}

		if (player.getHealth() < 10 && player.getHealth() > 0) {

		}

		if (player.getHealth() == 0) {

		}
	}
}
