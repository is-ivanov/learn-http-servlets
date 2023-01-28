package by.iivanov.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class HttpClientExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.build();

		HttpRequest getRequest = HttpRequest.newBuilder(URI.create("https://www.google.com"))
				.GET()
				.build();

		HttpRequest postRequest = HttpRequest.newBuilder(URI.create("https://www.google.com"))
				.POST(HttpRequest.BodyPublishers.ofFile(Path.of("path", "to", "file")))
				.build();


		HttpResponse<String> response = client.send(getRequest, BodyHandlers.ofString());
		System.out.println(response.headers());
		System.out.println(response.body());
	}
}
