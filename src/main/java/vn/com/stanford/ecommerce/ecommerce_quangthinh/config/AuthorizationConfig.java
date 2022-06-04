package vn.com.stanford.ecommerce.ecommerce_quangthinh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.interceptor.AuthorizationInterceptor;

@Configuration
public class AuthorizationConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/call-api/**", "/gio-hang/**");
    }
}
