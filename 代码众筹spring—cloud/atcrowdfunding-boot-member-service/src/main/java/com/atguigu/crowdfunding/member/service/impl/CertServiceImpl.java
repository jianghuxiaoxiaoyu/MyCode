package com.atguigu.crowdfunding.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crowdfunding.common.bean.Cert;
import com.atguigu.crowdfunding.member.dao.CertDao;
import com.atguigu.crowdfunding.member.service.CertService;

@Service
@Transactional
public class CertServiceImpl implements CertService {

	@Autowired
	private CertDao certDao ;
	
	@Override
	public List<Cert> queryCertsByAccttype(String accttype) {
		return certDao.queryCertsByAccttype(accttype);
	}

}
