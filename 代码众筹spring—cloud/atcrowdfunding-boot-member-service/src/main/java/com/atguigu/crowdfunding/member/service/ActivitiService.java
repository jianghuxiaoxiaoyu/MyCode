package com.atguigu.crowdfunding.member.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("atcrowdfunding-activiti-service")
public interface ActivitiService {

	@RequestMapping("/act/completTask")
	public void completTask(@RequestBody Map<String, Object> varables);

}
