package com.atguigu.crowdfunding.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.crowdfunding.common.bean.Member;
import com.atguigu.crowdfunding.common.bean.Ticket;
import com.atguigu.crowdfunding.common.controller.BaseController;
import com.atguigu.crowdfunding.service.MemberService;

@RestController
public class ActivitiController extends BaseController{

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService ;
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("/act/passApply")
	public void passApply(@RequestBody  Map<String, Object> varMap) {
		String taskid = (String)varMap.get("taskid");
		Integer memberid = (Integer)varMap.get("memberid");
		
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("flag", true);
		variables.put("memberid", memberid);
		
		taskService.complete(taskid, variables);
	}
	
	
	@RequestMapping("/act/queryTaskPageCount")
	public int queryTaskPageCount() {
		TaskQuery query = taskService.createTaskQuery();
		return (int)query.taskCandidateGroup("backcheck").count();
	}

	@RequestMapping("/act/queryTaskPageData")
	public List<Map<String, Object>> queryTaskPageData( @RequestBody Map<String, Object> varMap ) {
		
		int start = (int)varMap.get("start");
		int size = (int)varMap.get("size");
		
		TaskQuery query = taskService.createTaskQuery();
		List<Task> tasks = query.taskCandidateGroup("backcheck").listPage(start, size);
		
		List<Map<String, Object>> taskMapList = new ArrayList<Map<String, Object>>();
		
		for ( Task task : tasks ) {
			Map<String, Object> taskMap = new HashMap<String, Object>();
			
			taskMap.put("taskid", task.getId());
			taskMap.put("taskname", task.getName());
			// task ==> pi ==> ticket ==> member
			// 会员名称
			String piid = task.getProcessInstanceId();
			Ticket ticket = memberService.queryTicketByPiid(piid);
			Member member = memberService.queryMemberById(ticket.getMemberid());
			taskMap.put("membername", member.getUsername());
			taskMap.put("memberid", member.getId());
			// task ==> pd
			// 流程定义名称
			String pdid = task.getProcessDefinitionId();
			ProcessDefinition pd =
			    repositoryService
			       .createProcessDefinitionQuery()
			       .processDefinitionId(pdid).singleResult();
			taskMap.put("pdname", pd.getName());
			// 流程定义版本
			taskMap.put("pdversion", pd.getVersion());
			
			taskMapList.add(taskMap);
		}
		
		return taskMapList;

	}
	
	//=============================================================
	//通用方法,完成每一个任务.
	@RequestMapping("/act/completTask")
	public void completTask(@RequestBody Map<String, Object> varables) {
		
		String loginacct = (String)varables.get("loginacct");
		String piid = (String)varables.get("piid");
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		Task task = taskQuery.processInstanceId(piid).taskAssignee(loginacct).singleResult();
	
		taskService.complete(task.getId(),varables);
	}
	
	@RequestMapping("/act/startProcess/{loginacct}")
	public String startProcess(@PathVariable("loginacct") String loginacct) {
		
		ProcessDefinition pd = repositoryService
			.createProcessDefinitionQuery()
			.processDefinitionKey("auth")
			.latestVersion()
			.singleResult();
		
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("loginacct", loginacct);
		
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), variables);
		
		return pi.getId() ;
	}
	
	
	//=====================================================
	
	@RequestMapping("/act/deleteProDef/{deployid}")
	public void delete(@PathVariable("deployid") String deployid) {
		repositoryService.deleteDeployment(deployid, true);
	}
	
	
	@RequestMapping("/act/loadImgById/{id}")
	public byte[] loadImgById(@PathVariable("id") String id) { //流程定义id
		
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		
		ProcessDefinition pd = processDefinitionQuery.processDefinitionId(id).singleResult();
		
		String deploymentId = pd.getDeploymentId();
		String diagramResourceName = pd.getDiagramResourceName();
		
		InputStream in = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
		
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
		byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据 
		int rc = 0; 
		try {
			while ((rc = in.read(buff, 0, 100)) > 0) { 
			    swapStream.write(buff, 0, rc); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		byte[] in_b = swapStream.toByteArray(); //in_b为转换之后的结果

		
		return in_b ;
	}
	
	
	
	
	@RequestMapping("/act/deploy")
	public String deploy(@RequestParam("pdfile") MultipartFile file) {
		//repositoryService.createDeployment().addClasspathResource(file.getOriginalFilename()).deploy();
		try {
			repositoryService.createDeployment().addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
			return "部署成功!";
		} catch (IOException e) {
			e.printStackTrace();
			return "部署失败!";
		}
		
	}
	
	@RequestMapping("/act/queryCount")
	public int queryCount() {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		
		Long count = processDefinitionQuery.count();
		
		return count.intValue();
	}
	
	/**
	 * 将List<ProcessDefinition>集合直接转换json返回,会报异常.数据出现自关联.
	 * Could not write JSON: Direct self-reference leading to cycle; 
	 * nested exception is com.fasterxml.jackson.databind.JsonMappingException: 
	 * Direct self-reference leading to cycle 
	 * 传递参数:
	 * 		1.简单参数,直接采用路径方式即可.
	 * 		2.复杂参数:例如:map,entity class对象,可以用@RequestBody来修饰,从请求体中获取请求数据.
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	//@RequestMapping("/act/queryPageData/{startIndex}/{pagesize}")
	//public List<Map<String, Object>> queryPageData(@PathVariable("startIndex") Integer startIndex,@PathVariable("pagesize") Integer pagesize){
	@RequestMapping("/act/queryPageData")
	public List<Map<String, Object>> queryPageData(@RequestBody Map<String,Object> paramMap){
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		
		Integer startIndex = (Integer)paramMap.get("startindex");
		Integer pagesize = (Integer)paramMap.get("pagesize");
		
		List<ProcessDefinition> listPage = processDefinitionQuery.listPage(startIndex, pagesize); //limit ?,?

		List<Map<String, Object>> pdMapList = new ArrayList<Map<String,Object>>();
		
		for ( ProcessDefinition pd : listPage ) {
			Map<String, Object> pdMap = new HashMap<String, Object>();
			pdMap.put("id", pd.getId());
			pdMap.put("key", pd.getKey());
			pdMap.put("name", pd.getName());
			pdMap.put("version", pd.getVersion());
			pdMap.put("deployid", pd.getDeploymentId());
			pdMapList.add(pdMap);
		}
		
		return pdMapList ;
	}
	
}
