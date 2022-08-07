package com.wdzggroup.rbzy.erp.InsureContro.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigration implements WebMvcConfigurer {
    /**
     * 不拦截URL
     */
    private static String[] notLoginInterceptPaths = {"/error/**","/swagger-resources/**","/swagger-ui.html/**"};
    @Autowired
    private MyInterceptor myInterceptor;

    /**
     * 将拦截器注册到spring
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry   registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/InsureContro/userinfo/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
      /*  .excludePathPatterns("/InsureContro/userinfo/**")*/
    }

}
