package by.iivanov.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramRunner {

	public static void main(String[] args) throws IOException {
		InetAddress localhost = InetAddress.getByName("localhost");
		try (var datagramSocket = new DatagramSocket()) {
			var bytes = "Hello from UDP client".getBytes();
			var packet = new DatagramPacket(bytes, bytes.length, localhost, 7777);
			datagramSocket.send(packet);
		}

	}
}
