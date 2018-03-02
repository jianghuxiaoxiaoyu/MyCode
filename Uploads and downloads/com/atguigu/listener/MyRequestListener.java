package com.atguigu.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Request: 请求对象. 每次请求都会创建一个新的Request对象.
 *
 */
public class MyRequestListener implements ServletRequestListener, ServletRequestAttributeListener {

	public void requestInitialized(ServletRequestEvent arg0)  { 
		System.out.println("Request Init .....");
	}

    public void requestDestroyed(ServletRequestEvent arg0)  { 
    	System.out.println("Request Destroy.....");
    }

    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
    	System.out.println("Request Attribute Remove......");
    }


    public void attributeAdded(ServletRequestAttributeEvent arg0)  { 
    	System.out.println("Request Attribute Add......");
    }

    public void attributeReplaced(ServletRequestAttributeEvent arg0)  { 
    	System.out.println("Request Attribute Replace.....");
    }
	
}
