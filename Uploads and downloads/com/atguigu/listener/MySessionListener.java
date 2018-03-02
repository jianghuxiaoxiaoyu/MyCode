package com.atguigu.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
public class MySessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionListener() {
        // TODO Auto-generated constructor stub
    }
    
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	//当该方法执行，代表着新的Session对象创建了，可以粗略的认为新的用户进来的.
    	//尝试从ServletContext对象中获取在线人数
    	
    	ServletContext sc =  arg0.getSession().getServletContext();
    	Object obj = sc.getAttribute("count");
    	if(obj == null ) {
    		sc.setAttribute("count", 1);
    	}else {
    		sc.setAttribute("count", (Integer)obj + 1);
    	} 	
    	
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	ServletContext sc =  arg0.getSession().getServletContext();
    	Integer count = (Integer)sc.getAttribute("count");
    	
    	sc.setAttribute("count", count-1);
    }
	
}
