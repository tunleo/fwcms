package com.nexbis.fwcms.domain.ws;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class GetResultData extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5417785658279442090L;

	private String userName;
	private String token;
	private String requestId;

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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
