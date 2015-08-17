package com.nexbis.fwcms.domain.ws;

import java.util.List;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class AfisResult extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5978643260219513480L;

	private List<SubmitRequestResult> modelList;
	private String remarks;

	public List<SubmitRequestResult> getModelList() {
		return modelList;
	}

	public void setModelList(List<SubmitRequestResult> modelList) {
		this.modelList = modelList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
