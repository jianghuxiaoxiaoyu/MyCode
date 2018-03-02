package com.atguigu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContext: Servlet上下文对象 . Servlet容器启动就创建该对象，容器关闭就销毁该对象.
 *
 */
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    	System.out.println("MyServletContextListener创建了......");
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("ServletContext 销毁了.......");
    }
    /**
     * 该方法是在ServletContext进行初始化的时候会自动调用的.
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("ServletContext 初始化了......");
    }
	
}
