package com.atguigu.crowdfunding.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.MemberCert;

public interface MemberDao {

	@Select("select * from t_member where loginacct=#{loginacct}")
	Member queryByLoginacct(String loginacct);

	@Update("update t_member set accttype=#{accttype} where id=#{id}")
	int updateAccttype(Member member);

	@Update("update t_member set realname=#{realname},cardnum=#{cardnum},tel=#{tel} where id=#{id}")
	int updateBasicinfo(Member loginMember);

	void saveMemberCertList(List<MemberCert> mcs);

	@Select("select * from t_member where id=#{memberid}")
	Member getMemberById(Integer memberid);

	@Update("update t_member set email=#{email} where id=#{id}")
	int updateEamil(Member loginMember);

	@Update("update t_member set authstatus=#{authstatus} where id=#{id}")
	int updateMember(Member loginMember);

	List<MemberCert> queryMemberCertsByMemberId(Integer memberid);

	@Update("update t_member set authstatus='2' where id=#{id}")
	void updateMemberAuthStatus(Integer memberid);

}
