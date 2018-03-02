package com.atguigu.crowdfunding.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crowdfunding.common.bean.Ticket;

public interface TicketDao {

	@Select("select * from t_ticket where memberid=#{id} and status='0'")
	Ticket queryTicketByMemberid(Integer id);

	@Insert("insert into t_ticket(memberid,piid,status,authcode,pstep) values(#{memberid},#{piid},#{status},#{authcode},#{pstep})")
	void saveTicket(Ticket ticket);

	@Update("update t_ticket set pstep=#{pstep} where id=#{id}")
	void updatePStep(Ticket ticket);

	@Update("update t_ticket set pstep=#{pstep},authcode=#{authcode} where id=#{id}")
	void updateTicket(Ticket ticket);

	@Update("update t_ticket set status=#{status},pstep=#{pstep} where id=#{id}")
	void updateStatus(Ticket ticket);

	@Select("select * from t_ticket where piid=#{piid}")
	Ticket queryTicketByPiid(String piid);

}
