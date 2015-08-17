package com.nexbis.fwcms.service.request;

import java.util.List;

import org.apache.ws.security.WSSecurityException;

import com.nexbis.fwcms.domain.ws.GetResultResult;
import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.service.exception.FwcmsServiceException;

public interface RequestService {
	
	public List<SubmitRequestResult> submitRequest(String data) throws FwcmsServiceException,WSSecurityException;
	
	public GetResultResult getResult(String data) throws FwcmsServiceException;
}
