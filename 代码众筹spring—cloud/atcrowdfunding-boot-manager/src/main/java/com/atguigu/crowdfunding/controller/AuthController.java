package com.atguigu.crowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowdfunding.common.bean.MemberCert;
import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.common.util.Page;
import com.atguigu.crowdfunding.service.ActivitiService;
import com.atguigu.crowdfunding.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
	
	@Autowired
	private ActivitiService activitiService;
	
	@Autowired
	private MemberService memberService ;
	
	@RequestMapping("/index")
	public String index() {
		return "auth/index";
	}
	
	@ResponseBody
	@RequestMapping("/ok")
	public Object ok( String taskid, Integer memberid ) {
		start();
		
		try {
			// 通过认证申请
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("taskid", taskid);
			varMap.put("memberid", memberid);
			
			activitiService.passApply(varMap);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();

	}
	
	@RequestMapping("/detail")
	public String detail(Integer memberid, String taskid, Model model) {
		// 将数据保存到请求范围中，可以在freemarker页面中直接通过${}访问，也可以通过${RequestParameters["xxx"]}访问
		model.addAttribute("memberid", memberid);
		model.addAttribute("taskid", taskid);
		
		// 查询申请会员提交的资质文件数据
		List<MemberCert> mcfs = memberService.queryMemberCertsByMemberId(memberid);
		model.addAttribute("mcfs", mcfs);
		return "auth/detail";
	}

	
	@ResponseBody
	@RequestMapping("/loadPageData")
	public Object loadPageData( Integer pageno, Integer pagesize ) {
		start();
		
		try {
			
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("start", (pageno-1)*pagesize);
			varMap.put("size", pagesize);
			List<Map<String, Object>> data = activitiService.queryTaskPageData(varMap);
			Integer count = (Integer)activitiService.queryTaskPageCount();
			
			Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageno,pagesize);
			
			page.setDatas(data);
			page.setTotalsize(count);
			param("page",page);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}

}

