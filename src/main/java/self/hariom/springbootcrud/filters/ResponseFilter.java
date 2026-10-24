package self.hariom.springbootcrud.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class ResponseFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();

        // Add request id in response
        httpResponse.setHeader("X-Request-ID", requestId);

        // Used to modify response body (We should not do this)
        // We cannot modify outgoing response so we send a wrapper
        // On coming response we can modify wrapper
        // and send modified response
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper);

        byte[] originalBodyBytes = responseWrapper.getContentAsByteArray();

        String originalBody = new String(originalBodyBytes);

        String modifiedBody =
                """
                {
                    "originalResponse": %s,
                    "appName": "Spring crud app demo"
                }
                """.formatted(originalBody);

        // Remove old response body
        responseWrapper.resetBuffer();

        // Add modified response body
        responseWrapper.getWriter().write(modifiedBody);

        // Copy changes in original response
        responseWrapper.copyBodyToResponse();
    }
}
