package com.atguigu.i18n.test;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestI18N {
	
	/**
	 * 国际化主要的两个类:
	 * 	 Locale:  代表语言国家信息
	 *   ResourceBundle: 资源文件管理器, 根据Locale信息来加载不同的语言资源文件.
	 *   	语言文件:（国际化资源文件）
	 *   	     文件名:  baseName(基名)_语言代码_国家代码.properties   存放到src下.
	 *        文件中的内容: 不同国家的资源文件中的key是一致的
	 */
	@Test
	public void test() {
		//获取到当前的Locale对象
		Locale locale = Locale.getDefault();
		System.out.println(locale);  //zh_CN    en_US
		
		//1. 资源文件的基名    2. Locale对象
		ResourceBundle bundle =ResourceBundle.getBundle("i18n", locale);
		
		String login = bundle.getString("Login");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		
		System.out.println(login + " - " + username + " - " + password);
		
	}
	
}
