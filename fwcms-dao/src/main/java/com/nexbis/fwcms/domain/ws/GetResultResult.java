package com.nexbis.fwcms.domain.ws;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class GetResultResult extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 584458757825976202L;
	
	private String statusCode;
	private String remarks;
	
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
