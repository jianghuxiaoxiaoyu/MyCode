package com.atguigu.crowdfunding.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crowdfunding.common.bean.Ticket;

@FeignClient("atcrowdfunding-member-service")
public interface TicketService {

	@RequestMapping("/ticket/queryTicketByMemberid/{id}")
	public Ticket queryTicketByMemberid(@PathVariable("id") Integer id);

	@RequestMapping("/ticket/saveTicket")
	public void saveTicket(@RequestBody Ticket ticket);

	
	
}
