package com.atguigu.crowdfunding.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crowdfunding.common.bean.Ticket;
import com.atguigu.crowdfunding.member.dao.TicketDao;
import com.atguigu.crowdfunding.member.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao ;
	
	@Override
	public Ticket queryTicketByMemberid(Integer id) {
		return ticketDao.queryTicketByMemberid(id);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		ticketDao.saveTicket(ticket);
	}

}
