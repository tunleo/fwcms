package com.nexbis.fwcms.domain.ws;

import java.util.List;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class Data extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310434556592318498L;
	
	private String userName;
	private String token;
	private Integer listCount;
	private List<Model> modelList;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getListCount() {
		return listCount;
	}
	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}
	public List<Model> getModelList() {
		return modelList;
	}
	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

	
}
