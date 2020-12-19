package todo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String allowedUrls[] = { "login", "register", "styles.css", "allheaders.jsp" };

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Allow some specific requests without authentication
		boolean allowed = false;
		String url = req.getRequestURI();

		for (String u : allowedUrls)
			if (url.endsWith(u))
				allowed = true;

		if (allowed) {
			chain.doFilter(request, response);
		} else {
			// URL is not allowed unless authentication is done

			HttpSession session = req.getSession();
			if (session == null || session.getAttribute("username") == null) // Authentication is not done
				res.sendRedirect("/login");
			else
				chain.doFilter(request, response);
		}
	}

}
