package com.atguigu.crowdfunding.portal.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.crowdfunding.common.bean.Cert;
import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.MemberCert;
import com.atguigu.crowdfunding.common.bean.Ticket;
import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.common.util.Const;
import com.atguigu.crowdfunding.common.util.Data;
import com.atguigu.crowdfunding.portal.service.ActivitiService;
import com.atguigu.crowdfunding.portal.service.MemberService;
import com.atguigu.crowdfunding.portal.service.TicketService;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private ActivitiService activitiService ;
	
	@Autowired
	private MemberService memberService ;

	
	@ResponseBody
	@RequestMapping("/completeApply")
	public Object completeApply(String authcode,HttpSession session) {
		start();
		
		try {
			
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			Ticket ticket = ticketService.queryTicketByMemberid(loginMember.getId());
			
			if(ticket.getAuthcode().equals(authcode)) {
				
				
				loginMember.setAuthstatus("1");
				int count = memberService.completeApply(loginMember);
				if(count==1) {
					//loginMember.setAuthstatus("1");
					session.setAttribute(Const.LOGIN_MEMBER, loginMember);
					success(true);
				}else {
					success(false);
				}
				
			}else {
				success(false);
			}
			
			
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkemail(String email,HttpSession session) {
		start();
		
		try {
			
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			if(!email.equals(loginMember.getEmail())) {
				loginMember.setEmail(email);
				session.setAttribute(Const.LOGIN_MEMBER, loginMember);								
			}
			
			int count = memberService.sendEmail(loginMember);
			
			success(count==1);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		
		return end();
	}

	@ResponseBody
	@RequestMapping("/certUpload")
	public Object certUpload(Data ds,HttpSession session) {
		start();
		
		try {
			
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			
			List<MemberCert> mcs = ds.getMcs();
			
			for (MemberCert memberCert : mcs) {
				
				MultipartFile certfile = memberCert.getCertfile();
				String iconpath = "";
				// 文件处理 start				
				String fileName = certfile.getOriginalFilename(); //获取原始文件名称
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String uuid = UUID.randomUUID().toString();
				iconpath = uuid + suffix;//xxxx.jpg
				String path = "D:/resources/atcrowdfunding/img/cert/" + iconpath;
				//System.out.println("path = " + path);
				certfile.transferTo(new File(path));
				memberCert.setCertfile(null);
				memberCert.setIconpath(iconpath);
				memberCert.setMemberid(loginMember.getId());
			}
			
			
			memberService.saveMemberCertList(mcs);
			
			success(true);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo(Member member,HttpSession session) {
		start();
		
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());
			loginMember.setTel(member.getTel());
			
			//如果不进行session共享,往redis中存储的话,可以不用将member对象重新放置到session域中.
			//如果进行session共享,那么member对象是存储在redis中的,所以,必须重新放置session域中,否则redis中member对象还是一个旧对象.
			session.setAttribute(Const.LOGIN_MEMBER, loginMember);
			
			int count = memberService.updateBasicinfo(loginMember);
			
			success(count==1);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		
		return end();
	}
	
	
	@ResponseBody
	@RequestMapping("/updateAccttype")
	public Object updateAccttype(String accttype,HttpSession session) {
		start();
		
		try {
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			member.setAccttype(accttype);
			
			//如果不进行session共享,往redis中存储的话,可以不用将member对象重新放置到session域中.
			//如果进行session共享,那么member对象是存储在redis中的,所以,必须重新放置session域中,否则redis中member对象还是一个旧对象.
			session.setAttribute(Const.LOGIN_MEMBER, member);
			
			int count = memberService.updateAccttype(member);
			
			success(count==1);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		
		return end();
	}
	
	@RequestMapping("/apply")
	public String apply(HttpSession session,Map<String,Object> map) {
		// 根据登录会员id,查询流程单
		Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		Ticket ticket = ticketService.queryTicketByMemberid(member.getId());

		if(ticket == null) {
			// ○ 如果流程单为null
			// § 启动流程
			String piid =activitiService.startProcess(member.getLoginacct());
			
			// § 保存流程单
			ticket = new Ticket();
			ticket.setMemberid(member.getId());
			ticket.setPiid(piid);
			ticket.setStatus("0");
			ticket.setPstep("accttype");
			
			ticketService.saveTicket(ticket);
			// § 跳转到账户类型选择页面
			return "member/accttype";
		}else {
			// ○ 如果流程单不为null
			// § 根据流程单"pstep"判断,跳转到相关页面.
			String pstep = ticket.getPstep();
			
			if ( "accttype".equals(pstep) ) {
				return "member/accttype";
			} else if ( "basicinfo".equals(pstep) ) {
				return "member/basicinfo";
			} else if ( "certupload".equals(pstep) ) {
				
				// 查询当前用户需要传递的资质文件
				List<Cert> certs = memberService.queryCertsByAccttype(member.getAccttype());
				map.put("certs", certs);
				
				return "member/certupload";
			} else if ( "checkemail".equals(pstep) ) {
				return "member/checkemail";
			} else if ( "checkauthcode".equals(pstep) ) {
				return "member/checkauthcode";
			}

		}
		return "member/accttype";
	}

}
