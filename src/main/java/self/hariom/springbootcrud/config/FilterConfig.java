package self.hariom.springbootcrud.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.hariom.springbootcrud.filters.LoggingFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter());

        registrationBean.setOrder(3);
        registrationBean.addUrlPatterns("/api/*", "/admin*");

        return registrationBean;
    }

}
