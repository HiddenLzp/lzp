package com.lzp.demo.utils;

import java.util.HashMap;

/**
 * 返回结果对象
 */
public class ResultMap extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	public ResultMap() { }

	/**
	 * 返回成功
	 */
	public static ResultMap ok() {
		ResultMap resultMap = new ResultMap();
		resultMap.put("status", 200);
		return resultMap;
	}

	/**
	 * 返回成功
	 */
	public static ResultMap ok(int status) {
		ResultMap resultMap = new ResultMap();
		resultMap.put("status", status);
		resultMap.put("message", "操作成功！");
		return resultMap;
	}
	
	/**
	 * 返回成功
	 */
	public static ResultMap ok(int status, String message) {
		ResultMap resultMap = new ResultMap();
		resultMap.put("status", status);
		resultMap.put("message", message);
		return resultMap;
	}
	
	/**
	 * 返回失败
	 */
	public static ResultMap error() {
		return error("操作失败！");
	}
	
	/**
	 * 返回失败
	 */
	public static ResultMap error(String message) {
		return error(500, message);
	}

	/**
	 * 返回失败
	 */
	public static ResultMap error(int status, String message) {
		return ok(status, message);
	}
	
	/**
	 * 设置code
	 */
	public ResultMap setCode(int status){
		super.put("status", status);
		return this;
	}
	
	/**
	 * 设置message
	 */
	public ResultMap setMessage(String message){
		super.put("message", message);
		return this;
	}
	
	/**
	 * 放入object
	 */
	@Override
	public ResultMap put(String key, Object object){
		super.put(key, object);
		return this;
	}
}