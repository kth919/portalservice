package kr.ac.jejunu.spring;

import kr.ac.jejunu.hello.Hello;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by admin on 2016-05-21.
 */
public class HelloInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(HelloInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("*********  preHandle *********");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("********* postHandle *********");
    }


    @Override
public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ){
        logger.info("*********");
    }

}


