package self.hariom.springbootcrud.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
@Order(4)
public class RequestFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

//        BufferedReader reader = httpRequest.getReader();
//        StringBuilder body = new StringBuilder();
//
//        String line = reader.readLine();
//
//        while (line != null) {
//            body.append(line);
//            line = reader.readLine();
//        }
//
//        System.out.println(body);
//
        chain.doFilter(request, response);
    }
}
