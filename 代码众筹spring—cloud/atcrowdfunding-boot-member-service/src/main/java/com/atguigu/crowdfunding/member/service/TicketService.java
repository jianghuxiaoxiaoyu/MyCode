package com.atguigu.crowdfunding.member.service;

import com.atguigu.crowdfunding.common.bean.Ticket;

public interface TicketService {

	Ticket queryTicketByMemberid(Integer id);

	void saveTicket(Ticket ticket);

}
