package com.atguigu.crowdfunding.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.common.util.Const;
import com.atguigu.crowdfunding.portal.service.MemberService;

@Controller
public class PortalController extends BaseController {
	
	@Autowired
	private MemberService memberService ;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/member")
	public String member() {
		return "member";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	
	//登录系统
	@ResponseBody
	@RequestMapping("/doMemberLogin")
	public Object doMemberLogin( Member member, HttpSession session ) {
		start();
		
		try {
			Member dbMember = memberService.queryMemberByLoginacct(member.getLoginacct());
			if ( dbMember == null ) {
				message(Const.LOGIN_ERROR);
				success(false);
			} else {
				if ( dbMember.getUserpswd().equals(member.getUserpswd()) ) {
					session.setAttribute(Const.LOGIN_MEMBER, dbMember);
					success(true);
				} else {
					message(Const.LOGIN_ERROR_PASSWORD);
					success(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message(Const.LOGIN_ERROR);
			success(false);
		}
		
		return end();
	}

	
}
