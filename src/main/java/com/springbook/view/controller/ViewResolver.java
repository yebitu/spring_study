package com.springbook.view.controller;

public class ViewResolver {
	//접두사 (/)
	public String prefix;
	//접미사(.jsp)
	public String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
	

}
