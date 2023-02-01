package by.iivanov.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

	private final ExecutorService pool;
	private final int port;
	private boolean stopped;

	public HttpServer(int port, int poolSize) {
		this.port = port;
		this.pool = Executors.newFixedThreadPool(poolSize);
	}

	public void run() {
		try {
			var server = new ServerSocket(port);
			while (!stopped) {
				Socket socket = server.accept();
				System.out.println("Socket accepted");
				pool.submit(() -> processSocket(socket));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	private void processSocket(Socket socket) {
		try (socket;
			 DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			 DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
//			step 1 handle request
			System.out.println("Request: " + new String(inputStream.readNBytes(400)));

			Thread.sleep(10_000);
//			step 2 handle response
			byte[] body = Files.readAllBytes(Path.of("resources", "example.html"));
			var headers = """
					HTTP/1.1 200 OK
					content-type: text/html
					content-length: %s
					""".formatted(body.length).getBytes();
			outputStream.write(headers);
			outputStream.write(System.lineSeparator().getBytes());
			outputStream.write(body);
		} catch (IOException | InterruptedException e) {
			//TODO 29.01.2023 Ivanov Ivan: log error message
			e.printStackTrace();
		}
	}
}
