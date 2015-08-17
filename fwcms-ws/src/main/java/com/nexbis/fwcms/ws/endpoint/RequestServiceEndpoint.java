package com.nexbis.fwcms.ws.endpoint;

import org.apache.ws.security.WSSecurityException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;

import com.nexbis.fwcms.domain.ws.request.result.GetResultRequest;
import com.nexbis.fwcms.domain.ws.request.result.GetResultResponse;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestRequest;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestResponse;
import com.nexbis.fwcms.ws.endpoint.common.BaseServiceEndpoint;
import com.nexbis.fwcms.ws.endpoint.exception.FwcmsWsException;

public interface RequestServiceEndpoint extends BaseServiceEndpoint {
	
	
	public SubmitRequestResponse submitRequest(SubmitRequestRequest submitRequestRequest,SoapHeader requestHeader,MessageContext messageContext) throws WSSecurityException,FwcmsWsException;
	
	public GetResultResponse getResult(GetResultRequest getResultRequest,SoapHeader requestHeader,MessageContext messageContext) throws WSSecurityException,FwcmsWsException;
}
