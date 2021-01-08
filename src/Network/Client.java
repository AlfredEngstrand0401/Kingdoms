package Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Client {

	private static DatagramSocket socket;
	public static InetAddress address;
	public static int port;
	public static boolean running;

	public static HashMap<Multiplayer, String> playerdata = new HashMap<Multiplayer, String>();

	public Client(String address, int port) {
		try {
			this.address = InetAddress.getByName(address);
			this.port = port;

			socket = new DatagramSocket();
			running = true;
			send("\\con:" + Network.name + ":" + Network.x + ":" + Network.y + ":" + Network.z + ":" + Network.age + ":"
					+ Network.health);
			Client.listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send(String message) {
		try {
			message += "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void listen() {
		Thread listenThread = new Thread("listen thread") {
			public void run() {
				try {
					while (running) {

						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);

						String message = new String(data);
						message = message.substring(0, message.indexOf("\\e"));

						if (!isCommand(message, packet)) {
							System.out.println(message);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		listenThread.start();
	}

	public static boolean isCommand(String message, DatagramPacket packet) {

		if (message.startsWith("\\player_data:")) {
			String[] parts = message.split(":");
			String name = parts[1];
			if (!name.equals(Network.name)) {
				Network.generate = true;
				float x = Float.parseFloat(parts[2]);
				float y = Float.parseFloat(parts[3]);
				float z = Float.parseFloat(parts[4]);
				int age = Integer.parseInt(parts[5]);
				int health = Integer.parseInt(parts[5]);
				Multiplayer.createMultiplayer(name, x, y, z, age, health);
			}
			return true;
		}
		
		if(message.startsWith("\\player_update_data:")) {
			String[] parts = message.split(":");
			Network.generate = true;
			String name = parts[1];
			float x = Float.parseFloat(parts[2]);
			float y = Float.parseFloat(parts[3]);
			float z = Float.parseFloat(parts[4]);
			int age = Integer.parseInt(parts[5]);
			int health = Integer.parseInt(parts[5]);
			Multiplayer.createMultiplayer(name, x, y, z, age, health);
			return true;
		}

		if (message.startsWith("\\move:")) {
			String[] parts = message.split(":");

			String name = parts[1];
			String x = parts[2];
			String y = parts[3];
			String z = parts[4];

			String data = "\\move:" + x + ":" + y + ":" + z;

			for (int i = 0; i < Multiplayer.multiplayers.size(); i++) {

				Multiplayer player = Multiplayer.multiplayers.get(i);

				if (player.getName().equals(name)) {

					playerdata.put(player, data);

				}

			}

			return true;
		}

		return false;
	}

}
