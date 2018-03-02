package com.atguigu.crowdfunding.common.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	private ThreadLocal<Map<String, Object>> datas = new ThreadLocal<Map<String, Object>>();

	protected void start() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		datas.set(resultMap);
	}

	protected Object end() {
		Map<String, Object> resultMap = datas.get();
		datas.remove();
		return resultMap;
	}

	protected void success(boolean flg) {
		Map<String, Object> resultMap = datas.get();
		resultMap.put("success", flg);
	}

	protected void param(String key, Object val) {
		Map<String, Object> resultMap = datas.get();
		resultMap.put(key, val);
	}

	protected void message(String msg) {
		Map<String, Object> resultMap = datas.get();
		resultMap.put("message", msg);
	}
}