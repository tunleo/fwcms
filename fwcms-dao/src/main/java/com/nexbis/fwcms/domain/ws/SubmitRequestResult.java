package com.nexbis.fwcms.domain.ws;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class SubmitRequestResult  extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8123004468065798118L;
	
	private Integer index;
	private String requestId;
	
	public SubmitRequestResult() {
	}
	
	public SubmitRequestResult(Integer index,String requestId) {
		this.index = index;
		this.requestId = requestId;
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
}
