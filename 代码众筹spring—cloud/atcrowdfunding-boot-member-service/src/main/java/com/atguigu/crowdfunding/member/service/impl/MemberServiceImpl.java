package com.atguigu.crowdfunding.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.MemberCert;
import com.atguigu.crowdfunding.common.bean.Ticket;
import com.atguigu.crowdfunding.member.dao.MemberDao;
import com.atguigu.crowdfunding.member.dao.TicketDao;
import com.atguigu.crowdfunding.member.service.ActivitiService;
import com.atguigu.crowdfunding.member.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao ;
	
	@Autowired
	private TicketDao ticketDao ;
	
	@Autowired
	private ActivitiService activitiService ;
	
	@Override
	public Member queryByLoginacct(String loginacct) {
		return memberDao.queryByLoginacct(loginacct);
	}

	@Override
	public int updateAccttype(Member member) {
		// 更新账户类型
		// ○ 将账户类型封装到当前会员对象,根据会员对象,更新账户类型
		int count = memberDao.updateAccttype(member);
		// ○ 根据会员id查询流程单,更新流程单"pstep"步骤
		
		Ticket ticket= ticketDao.queryTicketByMemberid(member.getId());
		ticket.setPstep("basicinfo");
		ticketDao.updatePStep(ticket);
		
		// ○ 根据piid和loginacct完成流程任务,执行下一步(涉及服务间调用问题)
		
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("piid", ticket.getPiid());
		varables.put("loginacct",member.getLoginacct());
		activitiService.completTask(varables);

		return count;
	}

	@Override
	public int updateBasicinfo(Member loginMember) {
		// • 更新会员信息
		int count = memberDao.updateBasicinfo(loginMember);
		// • 更新流程单步骤"pstep"="certupload"
		Ticket ticket= ticketDao.queryTicketByMemberid(loginMember.getId());
		ticket.setPstep("certupload");
		ticketDao.updatePStep(ticket);
		
		// • 完成当前任务节点
		// ○ 设置流程变量${ flag == true}
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("piid", ticket.getPiid());
		varables.put("loginacct",loginMember.getLoginacct());
		varables.put("flag",true);
		
		activitiService.completTask(varables);
		return count;
	}

	@Override
	public void saveMemberCertList(List<MemberCert> mcs) {
		memberDao.saveMemberCertList(mcs);
		
		// • 更新流程单步骤"pstep"="checkemail"
		MemberCert mc = mcs.get(0);
		Integer memberid = mc.getMemberid();
		
		Ticket ticket= ticketDao.queryTicketByMemberid(memberid);
		ticket.setPstep("checkemail");
		ticketDao.updatePStep(ticket);
		
		// • 完成当前任务节点
		// ○ 设置流程变量${ flag == true}
		Member member = memberDao.getMemberById(memberid);
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("piid", ticket.getPiid());
		
		
		varables.put("loginacct",member.getLoginacct());
		varables.put("flag",true);
		
		activitiService.completTask(varables);
		
	}

	@Override
	public int sendEmail(Member loginMember) {
		//1.更新email
		int count = memberDao.updateEamil(loginMember);
		
		//2.更新流程步骤
		Ticket ticket= ticketDao.queryTicketByMemberid(loginMember.getId());
		ticket.setPstep("checkauthcode");
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=4;i++) {			
			sb.append(new Random().nextInt(10));
		}
		
		String authcode = sb.toString();

		ticket.setAuthcode(authcode);
		
		ticketDao.updateTicket(ticket); //根据id更新验证码和步骤
		
		//3.完成当前任务
		
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("piid", ticket.getPiid());
		varables.put("authcode", ticket.getAuthcode());
		varables.put("email", loginMember.getEmail());
		varables.put("loginacct",loginMember.getLoginacct());
		varables.put("flag",true);
		
		activitiService.completTask(varables);
		
		
		return count;
	}

	@Override
	public int completeApply(Member loginMember) {
		
		//1.更新用户状态: 0 -> 1
		int count = memberDao.updateMember(loginMember);
		
		//2.更新流程单: status  0->1   , pstep="ok"
		Ticket ticket= ticketDao.queryTicketByMemberid(loginMember.getId());
		ticket.setPstep("ok");
		ticket.setStatus("1");
		ticketDao.updateStatus(ticket); 
		
		//3.完成当前步骤
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("piid", ticket.getPiid());		
		varables.put("loginacct",loginMember.getLoginacct());		
		
		activitiService.completTask(varables);
		
		return count;
	}

	@Override
	public Ticket queryTicketByPiid(String piid) {
		return ticketDao.queryTicketByPiid(piid);
	}

	@Override
	public Member queryMemberById(Integer memberid) {
		return memberDao.getMemberById(memberid);
	}

	@Override
	public List<MemberCert> queryMemberCertsByMemberId(Integer memberid) {
		return memberDao.queryMemberCertsByMemberId(memberid);
	}

	@Override
	public void updateMemberAuthStatus(Integer memberid) {
		memberDao.updateMemberAuthStatus(memberid);
	}

}
