package com.atguigu.crowdfunding.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.atguigu.crowdfunding.common.util.Const;

/**
 * 在服务器启动时将项目上下文路径存放到application域中,给jsp使用.
 * 		ServletContext对象被创建,将项目上下文路径存放到application域中
 * 		ServletContext对象被销毁,将项目上下文路径从application域清除.
 */
@WebListener  //声明监听器对象,需要设置扫描注解@ServletComponentScan
//@WebServlet //声明Servlet对象
//@WebFilter //声明过滤器对象
public class StartUpSystemListener implements ServletContextListener {

	//@Autowired  //无法自动装配
	//private PermissionService permissionService ;
	
	//ServletContext对象被创建时执行
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//1.存放上下文路径.
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		System.out.println("contextPath="+contextPath);
		application.setAttribute(Const.APP_PATH, contextPath);
		
		//2.加载所有许可对象

		
		//3.加载广告数据
		
		//4.热点项目
		
		//...
		
	}

	//ServletContext对象被销毁时执行.
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
