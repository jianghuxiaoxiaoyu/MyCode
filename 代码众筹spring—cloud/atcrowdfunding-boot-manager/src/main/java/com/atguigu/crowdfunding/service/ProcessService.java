package com.atguigu.crowdfunding.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("atcrowdfunding-activiti-service")
public interface ProcessService {
	
	@RequestMapping("/act/queryCount")
	public int queryCount();
	
	
	/*@RequestMapping("/act/queryPageData/{startIndex}/{pagesize}")
	public List<Map<String, Object>> queryPageData(
					@PathVariable("startIndex") Integer startIndex,
					@PathVariable("pagesize") Integer pagesize);*/
	
	@RequestMapping("/act/queryPageData")
	public List<Map<String, Object>> queryPageData(@RequestBody Map<String,Object> paramMap);

	@RequestMapping("/act/deleteProDef/{deployid}")
	public void delete(@PathVariable("deployid") String deployid);
}
