package com.atguigu.crowdfunding.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.Ticket;

@FeignClient("atcrowdfunding-member-service")
public interface MemberService {

	@RequestMapping("/member/queryTicketByPiid/{piid}")
	Ticket queryTicketByPiid(@PathVariable("piid")String piid);

	@RequestMapping("/member/queryMemberById/{memberid}")
	Member queryMemberById(@PathVariable("memberid")Integer memberid);

	@RequestMapping("/member/updateMemberAuthStatus/{memberid}")
	void updateMemberAuthStatus(@PathVariable("memberid") Integer memberid);

}

