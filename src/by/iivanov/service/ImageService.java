package by.iivanov.service;

import by.iivanov.util.PropertiesUtil;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ImageService {

	private static final ImageService INSTANCE = new ImageService();

	private final String basePath = PropertiesUtil.get("image.base.url");

	@SneakyThrows
	public void upload(String imagePath, InputStream imageContent) {
		Path imageFullPath = Path.of(basePath, imagePath);
		try (imageContent) {
			Files.createDirectories(imageFullPath.getParent());
			Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		}
	}

	public static ImageService getInstance() {
		return INSTANCE;
	}
}
