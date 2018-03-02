package com.atguigu.crowdfunding.common.util;

import java.util.List;

public class Page<T> {
	private List<T> datas; // 当前页数据

	private int totalno; // 总页码
	private int pageno; // 当前页
	private int totalsize; // 总记录数
	private int pagesize; // 每页条数

	public Page(Integer pageno,Integer pagesize) {
		if(pageno<=0) {
			this.pageno = 1 ;
		}else {
			this.pageno = pageno ;
		}
		
		if(pagesize<=0) {
			this.pagesize = 10 ;
		}else {
			this.pagesize = pagesize ;
		}
	}
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getTotalno() {
		return totalno;
	}

	private void setTotalno(int totalno) {
		this.totalno = totalno;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
		//计算总页码
		this.totalno = (totalsize%pagesize) > 0 ? ((totalsize/pagesize)+1) : (totalsize/pagesize) ;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getStartindex() {
		return (pageno-1)*pagesize;
	}

}
