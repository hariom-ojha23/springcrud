package self.hariom.springbootcrud.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");
        String apiKey = httpRequest.getHeader("x-api-key");

        // Validate auth token
        if (token == null || !token.equals("12345")) {
            System.out.println("Unauthorized request");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Validate Api Key
        if (apiKey == null || !apiKey.equals("secret")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "  \"message\": \"Invalid API Key\"\n" +
                            "}"
            );
            return;
        }

        chain.doFilter(request, response);
    }
}
