package com.bgmiastoto.interseptors;

import com.bgmiastoto.services.AppVisitedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AppVisitedInterceptor extends HandlerInterceptorAdapter {

    private int counter;

    private final AppVisitedService service;

    @Autowired
    public AppVisitedInterceptor(AppVisitedService service) {
        this.service = service;
}

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        this.counter++;
        if (counter % 10 == 0) {
            this.service.updateCounter(counter);
        }
        return true;
    }
}
