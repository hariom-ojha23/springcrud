package self.hariom.springbootcrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import self.hariom.springbootcrud.interceptors.LoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public LoggingInterceptor loggingInterceptor;
//    public AuthenticationInterceptor authenticationInterceptor;

    public WebConfig(
            LoggingInterceptor loggingInterceptor
//            AuthenticationInterceptor authenticationInterceptor
    ) {
        this.loggingInterceptor = loggingInterceptor;
//        this.authenticationInterceptor = authenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/admin/**")
                .order(1);

//        registry.addInterceptor(authenticationInterceptor)
//                .addPathPatterns("/api/**")
//                .excludePathPatterns("/api/auth/**", "/api/public/**")
//                .order(2);

    }
}
