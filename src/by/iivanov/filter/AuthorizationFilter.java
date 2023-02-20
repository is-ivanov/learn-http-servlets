package by.iivanov.filter;

import by.iivanov.dto.UserDto;
import by.iivanov.util.UrlPath;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	private static final Set<String> PUBLIC_PATH = Set.of(UrlPath.LOGIN, UrlPath.REGISTRATION, UrlPath.IMAGES);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String uri = ((HttpServletRequest) servletRequest).getRequestURI();
		if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			String previousPage = ((HttpServletRequest) servletRequest).getHeader("referer");
			((HttpServletResponse) servletResponse).sendRedirect(previousPage != null ? previousPage : UrlPath.LOGIN);
		}
	}

	private boolean isUserLoggedIn(ServletRequest servletRequest) {
		UserDto userDto = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
		return userDto != null;
	}

	private boolean isPublicPath(String uri) {
		return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
	}
}
