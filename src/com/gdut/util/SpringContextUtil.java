package com.gdut.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringContextUtil implements ApplicationContextAware  {
    private static ApplicationContext context;
    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext contex)
            throws BeansException {
        // TODO Auto-generated method stub
        this.context=contex;
    }
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
 
     
    public static String getMessage(String key){
        return context.getMessage(key, null, Locale.getDefault());
    }
 
}