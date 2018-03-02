package com.atguigu.mybatis.test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.atguigu.mybatis.beans.Employee;
import com.atguigu.mybatis.beans.EmployeeExample;
import com.atguigu.mybatis.beans.EmployeeExample.Criteria;
import com.atguigu.mybatis.dao.EmployeeMapper;

public class TestMyBatis {

	/**
	 * 获取SqlSessionFactory对象
	 */
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}	
	
	@Test
	public void testMBG() throws Exception {
		   List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   File configFile = new File("mbg.xml");
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
	}
	
	@Test
	public void testMybatis3Simple()  throws Exception{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		try {
			EmployeeMapper mapper= session.getMapper(EmployeeMapper.class);
			
			//查询员工的名字中带有e字母并且员工性别是1   或者 email中带有d字母的。
			EmployeeExample example = new EmployeeExample();
			//Criteria 
			Criteria criteria  = example.createCriteria();
			
			criteria.andLastNameLike("%e%");
			criteria.andGenderEqualTo("1");
			
			//or的条件需要再用一个criteria来封装
			
			Criteria criteria2 = example.createCriteria();
			
			criteria2.andEmailLike("%d%");
			
			//or条件的criteria 需要or到example中
			example.or(criteria2);
			
			
			List<Employee> emps = mapper.selectByExample(example);
			
			for (Employee employee : emps) {
				System.out.println(employee);
			}
			
		} finally {
			session.close();
		}
	}
	

	
	
	
}
