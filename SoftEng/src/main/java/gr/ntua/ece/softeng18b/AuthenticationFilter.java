package gr.ntua.ece.softeng18b;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthenticationFilter
 */

public class AuthenticationFilter implements Filter {

    public static final String AUTHENTICATION_HEADER = "X-OBSERVATORY-AUTH";

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String token = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
        AuthenticationService authenticationService = new AuthenticationService();

        // pass the request along the filter chain
        if (httpServletRequest.getMethod().equals("GET") || authenticationService.validateToken(token))
            chain.doFilter(request, response);
        else {
          HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }


    public void destroy() {
        //we can close resources here
    }

}
