package com.atguigu.crowdfunding.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.crowdfunding.common.bean.Ticket;
import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.member.service.TicketService;

@RestController
public class TicketController extends BaseController {

	@Autowired
	private TicketService ticketService ;
	
	@RequestMapping("/ticket/queryTicketByMemberid/{id}")
	public Ticket queryTicketByMemberid(@PathVariable("id") Integer id) {		
		return ticketService.queryTicketByMemberid(id) ;
	}

	@RequestMapping("/ticket/saveTicket")
	public void saveTicket(@RequestBody Ticket ticket) {
		ticketService.saveTicket(ticket);
	}
}
