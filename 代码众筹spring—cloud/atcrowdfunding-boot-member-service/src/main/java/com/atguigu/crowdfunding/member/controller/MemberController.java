package com.atguigu.crowdfunding.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.crowdfunding.common.bean.Cert;
import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.MemberCert;
import com.atguigu.crowdfunding.common.bean.Ticket;
import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.member.service.CertService;
import com.atguigu.crowdfunding.member.service.MemberService;

@RestController
public class MemberController extends BaseController{
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CertService certService ;

	
	@RequestMapping("/member/updateMemberAuthStatus/{memberid}")
	void updateMemberAuthStatus(@PathVariable("memberid") Integer memberid) {
		memberService.updateMemberAuthStatus(memberid);
	}
	
	
	@RequestMapping("/member/queryMemberCertsByMemberId/{id}")
	List<MemberCert> queryMemberCertsByMemberId(@PathVariable("id")Integer memberid) {
		return memberService.queryMemberCertsByMemberId(memberid);
	}

	
	@RequestMapping("/member/queryTicketByPiid/{piid}")
	public Ticket queryTicketByPiid(@PathVariable("piid")String piid) {
		return memberService.queryTicketByPiid(piid);
	}

	@RequestMapping("/member/queryMemberById/{memberid}")
	public Member queryMemberById(@PathVariable("memberid")Integer memberid) {
		return memberService.queryMemberById(memberid);
	}

	
	//=====================================================
	
	
	@RequestMapping("/member/completeApply")
	public int completeApply(@RequestBody Member loginMember) {
		return memberService.completeApply(loginMember);
	}
	
	
	@RequestMapping("/member/sendEmail")
	public int sendEmail(@RequestBody Member loginMember) {
		return memberService.sendEmail(loginMember);
	}
	
	@RequestMapping("/member/saveMemberCertList")
	public void saveMemberCertList(@RequestBody List<MemberCert> mcs) {		
		memberService.saveMemberCertList(mcs);
	}
	
	@RequestMapping("/member/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member loginMember) {		
		return memberService.updateBasicinfo(loginMember);
	}
	
	@RequestMapping("/member/queryCertsByAccttype/{accttype}")
	public List<Cert> queryCertsByAccttype(@PathVariable("accttype") String accttype){		
		return certService.queryCertsByAccttype(accttype);
	}
	
	@RequestMapping("/member/query/{loginacct}")
	public Member queryMemberByLoginacct(@PathVariable("loginacct")String loginacct) {
		return memberService.queryByLoginacct(loginacct);//在java程序中做密码判断验证
	}
	
	@RequestMapping("/member/updateAccttype")
	public int updateAccttype(@RequestBody Member member) {
		return memberService.updateAccttype(member);
	}
}
