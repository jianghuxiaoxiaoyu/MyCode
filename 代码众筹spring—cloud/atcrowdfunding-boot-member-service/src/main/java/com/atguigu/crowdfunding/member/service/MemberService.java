package com.atguigu.crowdfunding.member.service;

import java.util.List;

import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.MemberCert;
import com.atguigu.crowdfunding.common.bean.Ticket;

public interface MemberService {
	Member queryByLoginacct(String loginacct);

	int updateAccttype(Member member);

	int updateBasicinfo(Member loginMember);

	void saveMemberCertList(List<MemberCert> mcs);

	int sendEmail(Member loginMember);

	int completeApply(Member loginMember);

	Ticket queryTicketByPiid(String piid);

	Member queryMemberById(Integer memberid);

	List<MemberCert> queryMemberCertsByMemberId(Integer memberid);

	void updateMemberAuthStatus(Integer memberid);
}
