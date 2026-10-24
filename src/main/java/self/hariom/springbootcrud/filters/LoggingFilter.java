package self.hariom.springbootcrud.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Log request
        System.out.println("Incoming request: " +
                httpRequest.getMethod() + " " +
                httpRequest.getRequestURI()
        );

        try {
            chain.doFilter(request, response);
        } finally {
            // Request duration
            long requestDuration = System.currentTimeMillis() - startTime;

            // Log response status
            System.out.println("Response status: " + httpResponse.getStatus());
            System.out.println("Request duration: " + requestDuration);
        }
    }
}
