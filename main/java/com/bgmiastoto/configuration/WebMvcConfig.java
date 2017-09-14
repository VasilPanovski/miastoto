package com.bgmiastoto.configuration;

import com.bgmiastoto.interseptors.ActionExecutionInterceptor;
import com.bgmiastoto.interseptors.AppVisitedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final ActionExecutionInterceptor activityInterceptor;

    private final AppVisitedInterceptor appVisitedInterceptor;

    @Autowired
    public WebMvcConfig(ActionExecutionInterceptor activityInterceptor, AppVisitedInterceptor appVisitedInterceptor) {
        this.activityInterceptor = activityInterceptor;
        this.appVisitedInterceptor = appVisitedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.activityInterceptor);
        registry.addInterceptor(this.appVisitedInterceptor).addPathPatterns("/");
    }
}
