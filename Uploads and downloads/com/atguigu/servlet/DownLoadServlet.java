package com.atguigu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理用户的下载请求
		ServletContext sc = this.getServletContext();
		String fileName = "haibushiyinweinizhangdebuhaokan.mp3";
		//获取文件的真实路径
		String realPath = sc.getRealPath("music"+File.separator+fileName);
		System.out.println("realPath:" + realPath);
		//设置MIMEType: 文件在服务器上对应的类型. 
		String type = sc.getMimeType(fileName);  //audio/mpeg
		System.out.println("mimeType:" + type);
		
		//告诉浏览器文件的类型
		response.setContentType(type);   
		
		fileName  ="还不是因为你们长的不好看.mp3";
		//对于火狐浏览器要进行特殊的处理
		//获取请求头中的User-Agent信息
		String agent = request.getHeader("User-Agent");
		if(agent.contains("Firefox")) {
			//火狐
			fileName  = "=?utf-8?B?"+new BASE64Encoder().encode(fileName.getBytes("utf-8"))+"?=";
			System.out.println("fileName FireFox:" + fileName);
		}else {
			//其他
			fileName  = URLEncoder.encode(fileName,"UTF-8");
		}
		
		System.out.println("fileName:" + fileName);
		
		
		//设置下载时的文件名提示
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		
		//通过文件流读取要下载的文件
		FileInputStream  fis = new FileInputStream(realPath);
		//通过输出流写给浏览器
		OutputStream os =   response.getOutputStream();
		
		int len ; 
		byte [] b = new byte[1024];
		
		while((len=fis.read(b))!=-1) {
			os.write(b,0,len);
		}
		
		//关闭流
		os.close();
		fis.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
