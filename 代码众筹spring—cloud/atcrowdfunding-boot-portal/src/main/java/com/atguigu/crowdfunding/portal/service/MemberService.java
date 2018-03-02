package com.atguigu.crowdfunding.portal.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crowdfunding.common.bean.Cert;
import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.MemberCert;

@FeignClient("atcrowdfunding-member-service")
public interface MemberService {
	@RequestMapping("/member/query/{loginacct}")
	public Member queryMemberByLoginacct(@PathVariable("loginacct")String loginacct);

	@RequestMapping("/member/updateAccttype")
	public int updateAccttype(@RequestBody Member member);

	@RequestMapping("/member/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member loginMember);

	@RequestMapping("/member/queryCertsByAccttype/{accttype}")
	public List<Cert> queryCertsByAccttype(@PathVariable("accttype") String accttype);

	@RequestMapping("/member/saveMemberCertList")
	public void saveMemberCertList(@RequestBody List<MemberCert> mcs);

	@RequestMapping("/member/sendEmail")
	public int sendEmail(@RequestBody Member loginMember);

	@RequestMapping("/member/completeApply")
	public int completeApply(@RequestBody Member loginMember);
}
