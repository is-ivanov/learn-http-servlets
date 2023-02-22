package by.iivanov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("content-disposition", "attachment; filename=\"filename.txt\"");
		resp.setContentType("application/json");
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

//		byte[] resources = Files.readAllBytes(Path.of("resources", "first.json")); Так работать не будет, т.к. работаем мнутри томкэт
		try (ServletOutputStream outputStream = resp.getOutputStream();
			 InputStream stream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")) {
			outputStream.write(stream.readAllBytes());
		}

	}
}
