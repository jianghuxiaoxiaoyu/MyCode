package com.atguigu.crowdfunding.common.bean;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class MemberCert implements Serializable{
	private Integer memberid;
	private Integer certid;
	private String iconpath;
	private MultipartFile certfile;
	
	private String certname ;
	
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Integer getCertid() {
		return certid;
	}
	public void setCertid(Integer certid) {
		this.certid = certid;
	}
	public String getIconpath() {
		return iconpath;
	}
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
	public MultipartFile getCertfile() {
		return certfile;
	}
	public void setCertfile(MultipartFile certfile) {
		this.certfile = certfile;
	}
	public String getCertname() {
		return certname;
	}
	public void setCertname(String certname) {
		this.certname = certname;
	}
	
	

}
