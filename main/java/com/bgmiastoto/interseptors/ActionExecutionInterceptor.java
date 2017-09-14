package com.bgmiastoto.interseptors;

import com.bgmiastoto.models.viewModels.LogView;
import com.bgmiastoto.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ActionExecutionInterceptor extends HandlerInterceptorAdapter {

    private static final String ACTION_START_TIME = "actionStartTime";
    private static final int MAX_EXECUTION_TIME = 5000;

    private final LogService logService;

    @Autowired
    public ActionExecutionInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setAttribute(ACTION_START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        long currentTime = System.currentTimeMillis();
        long actionStartTime = (long) httpServletRequest.getAttribute(ACTION_START_TIME);

        long overallExecutionTimeInSec = currentTime - actionStartTime;
        if (overallExecutionTimeInSec > MAX_EXECUTION_TIME) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String actionName = handlerMethod.getBean().getClass().getName();
            LogView logView = new LogView(actionName, overallExecutionTimeInSec);

            this.logService.save(logView);
        }
    }
}
