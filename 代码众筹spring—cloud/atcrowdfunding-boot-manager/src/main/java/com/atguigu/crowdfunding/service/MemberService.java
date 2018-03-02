package com.atguigu.crowdfunding.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crowdfunding.common.bean.MemberCert;

@FeignClient("atcrowdfunding-member-service")
public interface MemberService {

	@RequestMapping("/member/queryMemberCertsByMemberId/{id}")
	List<MemberCert> queryMemberCertsByMemberId(@PathVariable("id")Integer memberid);

}
