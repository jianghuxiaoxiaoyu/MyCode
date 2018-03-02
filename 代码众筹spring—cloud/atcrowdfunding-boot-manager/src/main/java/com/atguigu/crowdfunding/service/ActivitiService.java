package com.atguigu.crowdfunding.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("atcrowdfunding-activiti-service")
public interface ActivitiService {
	@RequestMapping("/act/queryTaskPageCount")
	public int queryTaskPageCount();

	@RequestMapping("/act/queryTaskPageData")
	public List<Map<String, Object>> queryTaskPageData( @RequestBody Map<String, Object> varMap );

	@RequestMapping("/act/passApply")
	public void passApply(@RequestBody  Map<String, Object> varMap);

}
