package com.atguigu.crowdfunding.member.service;

import java.util.List;

import com.atguigu.crowdfunding.common.bean.Cert;

public interface CertService {

	List<Cert> queryCertsByAccttype(String accttype);

}
