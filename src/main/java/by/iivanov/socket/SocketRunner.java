package by.iivanov.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketRunner {

	public static void main(String[] args) throws IOException {
		InetAddress googleAddress = InetAddress.getByName("localhost");
		try (Socket socket = new Socket(googleAddress, 7777);
			 DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			 DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
			outputStream.writeUTF("Hello, world!");
			System.out.println("Response from server: " + inputStream.readUTF());
		}

	}
}