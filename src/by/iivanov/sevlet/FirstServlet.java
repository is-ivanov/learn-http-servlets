package by.iivanov.sevlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/first")
public class FirstServlet  extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		Map<String, String[]> parameterMap = req.getParameterMap();

		var headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			var header = headerNames.nextElement();
			System.out.println(req.getHeader(header));
		}

		resp.setContentType("text/html; charset=UTF-8");
//		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setHeader("token", "12345");
		try (PrintWriter writer = resp.getWriter()) {
			writer.write("<h1>Привет с первого сервлета!</h1>");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameterMap = req.getParameterMap();
		System.out.println(parameterMap.toString());
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
