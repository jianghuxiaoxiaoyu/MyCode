package com.atguigu.crowdfunding.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.atguigu.crowdfunding.common.util.ApplicationContextUtils;
import com.atguigu.crowdfunding.service.MemberService;

public class PassListener implements ExecutionListener {

	//不能直接进行自动装配,因为监听器对象是被流程框架创建的.
	//@Autowired
	//private MemberService memberService ;
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		System.out.println("PassListener...");

		Integer id = (Integer)execution.getVariable("memberid");
		MemberService memberService = ApplicationContextUtils.applicationContext.getBean(MemberService.class);
		memberService.updateMemberAuthStatus(id);

	}

}
