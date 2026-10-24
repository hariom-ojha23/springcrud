package self.hariom.springbootcrud.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        System.out.println("PreHandle called");

        System.out.print("Incoming req from interceptor: ");
        System.out.println(request.getMethod() + " " + request.getRequestURI());
        System.out.println("Req Query: " + request.getQueryString());
        System.out.println("Client IP: " + request.getRemoteAddr());

        if (handler instanceof  HandlerMethod method) {
            String controllerName = method.getBeanType().getName();
            String methodName = method.getMethod().getName();

            System.out.println("ControllerName: " + controllerName);
            System.out.println("MethodName: " + methodName);
        }

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable Exception ex
    ) throws Exception {
        System.out.println("AfterCompletion called");
        System.out.println("Interceptor Response Status: " + response.getStatus());
    }
}
