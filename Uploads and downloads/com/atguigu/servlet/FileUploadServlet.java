package com.atguigu.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取保存上传文件的 文件夹的真实路径.
		ServletContext sc = getServletContext();
		String realPath = sc.getRealPath("upload");
		System.out.println("realPath:" + realPath);
		
		
		//处理上传的文件 
		//FileUpload技术来处理上传   ，需要依赖commons-io包
		//1.创建一个工厂类
		 DiskFileItemFactory itemFactory  =  new DiskFileItemFactory();
		//2.使用DiskFileItemFactory创建ServletFileUpload对象.
		    // ServletFileUpload的作用: 将浏览器传过来的流解析成一个个的表单项,根据表单项对象的属性判断当前的表单项是普通文本域还是上传域.
		 ServletFileUpload fileUpload  = new ServletFileUpload(itemFactory);
		 //3设置一些上传的参数.
		 //fileUpload.setFileSizeMax(fileSizeMax);
		 //fileUpload.setSizeMax(sizeMax);
		 
		 //4.使用解析器对象解析请求
		 try {
			List<FileItem> list = fileUpload.parseRequest(request);
			for (FileItem fileItem : list) {
				//fileItem的相关的方法
				/*System.out.println("表单项的name属性值:" +fileItem.getFieldName());
				System.out.println("文件名:" + fileItem.getName());
				System.out.println("表单项的value属性值:" + fileItem.getString("utf-8"));
				//true: 普通的文本框     false: 上传域
				System.out.println("是否为普通的文本框:" +fileItem.isFormField());*/
				
				if(fileItem.isFormField()) {
					//普通的文本框
					System.out.print("表单项的name属性值:" + fileItem.getFieldName());
					System.out.println("表单项的value属性值:" +fileItem.getString("utf-8"));
				}else {
					//获取上传文件的文件名
					String fileName = fileItem.getName();
					//处理重名会覆盖的问题
					String uuid  = UUID.randomUUID().toString().replace("-", "");
					
					fileName  = uuid +"_"+ fileName ;
					
					//上传域
					File targetFile = new File(realPath+File.separator+fileName);
					
					
					try {
						fileItem.write(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
