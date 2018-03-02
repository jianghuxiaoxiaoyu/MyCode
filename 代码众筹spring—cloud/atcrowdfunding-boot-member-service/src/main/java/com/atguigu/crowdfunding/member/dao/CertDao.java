package com.atguigu.crowdfunding.member.dao;

import java.util.List;

import com.atguigu.crowdfunding.common.bean.Cert;

public interface CertDao {

	List<Cert> queryCertsByAccttype(String accttype);

}
