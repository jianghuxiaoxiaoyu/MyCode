package com.atguigu.crowdfunding.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.common.util.Page;
import com.atguigu.crowdfunding.service.ProcessService;

@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController {

	@Autowired
	private ProcessService processService;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/index")
	public String index() {
		return "process/index";
	}
	
	@RequestMapping("/view")
	public String view() {
		return "process/view";
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( String deployid ) { //流程部署id.
		start();
		try {
			processService.delete(deployid);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}

	
	@RequestMapping("/loadImg")
	public void loadImg(String id,HttpServletResponse response) {
		try {
			// 通过响应对象返回图形信息
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			                    
			String url = "http://atcrowdfunding-activiti-service/act/loadImgById/"+id;
			ResponseEntity<byte[]> resp = restTemplate.exchange( url, HttpMethod.POST,  new HttpEntity<byte[]>(headers), byte[].class); 
			byte[] result = resp.getBody();

			InputStream in = new ByteArrayInputStream(result); //内存流
			OutputStream out = response.getOutputStream();
			
			int i = -1;
			while ( (i = in.read()) != -1 ) {
				out.write(i);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}
	

	@ResponseBody
	@RequestMapping("/upload")
	public Object upload(HttpServletRequest req) {
		start();

		try {
			MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;

			MultipartFile file = request.getFile("procDefFile");

			// 获取的是表单中文件域的name属性值
			System.out.println(file.getName());
			// 获取的是传递的文件名称
			System.out.println(file.getOriginalFilename());

			String uuid = UUID.randomUUID().toString();
			String fileName = file.getOriginalFilename();
			final File tempFile = File.createTempFile(uuid, fileName.substring(fileName.lastIndexOf(".")));

			file.transferTo(tempFile);

			FileSystemResource resource = new FileSystemResource(tempFile);
			MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
			param.add("pdfile", resource);
			String s = restTemplate.postForObject("http://atcrowdfunding-activiti-service/act/deploy", param,String.class);
			System.out.println("result string = " + s);
			tempFile.delete();

			success(true);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}

		return end();
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/queryPage") public Object
	 * queryPage(@RequestParam(value="pageno",required=false,defaultValue="1")
	 * Integer pageno,
	 * 
	 * @RequestParam(value="pagesize",required=false,defaultValue="2") Integer
	 * pagesize) { start();
	 * 
	 * try { Page page = new Page(pageno,pagesize); Integer startindex =
	 * page.getStartindex();
	 * 
	 * List<Map<String,Object>> list = processService.queryPageData(startindex,
	 * pagesize); int totalsize = processService.queryCount();
	 * 
	 * page.setDatas(list); page.setTotalsize(totalsize);
	 * 
	 * param("page", page);
	 * 
	 * success(true); } catch (Exception e) { success(false); e.printStackTrace(); }
	 * 
	 * return end(); }
	 */

	@ResponseBody
	@RequestMapping("/queryPage")
	public Object queryPage(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "2") Integer pagesize) {
		start();

		try {
			Page page = new Page(pageno, pagesize);
			Integer startindex = page.getStartindex();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startindex", startindex);
			paramMap.put("pagesize", pagesize);

			List<Map<String, Object>> list = processService.queryPageData(paramMap);
			int totalsize = processService.queryCount();

			page.setDatas(list);
			page.setTotalsize(totalsize);

			param("page", page);

			success(true);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}

		return end();
	}
}
