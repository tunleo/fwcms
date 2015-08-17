package com.nexbis.fwcms.ws.endpoint.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ws.security.WSSecurityException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

import com.nexbis.fwcms.domain.enums.EnumStatusResult;
import com.nexbis.fwcms.domain.ws.AfisResult;
import com.nexbis.fwcms.domain.ws.GetResultResult;
import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.domain.ws.request.result.GetResultRequest;
import com.nexbis.fwcms.domain.ws.request.result.GetResultResponse;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestRequest;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestResponse;
import com.nexbis.fwcms.service.exception.FwcmsServiceException;
import com.nexbis.fwcms.service.request.RequestService;
import com.nexbis.fwcms.ws.endpoint.RequestServiceEndpoint;
import com.nexbis.fwcms.ws.endpoint.common.AbstractServiceEndpoint;
import com.nexbis.fwcms.ws.endpoint.exception.FwcmsWsException;

@Endpoint
public class RequestServiceEndpointImpl extends AbstractServiceEndpoint
		implements RequestServiceEndpoint {

	private static Logger logger = LoggerFactory
			.getLogger(RequestServiceEndpointImpl.class);

	@Autowired
	private RequestService requestService;

	@Override
	@PayloadRoot(localPart = SUBMIT_REQUEST_REQUEST, namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	SubmitRequestResponse submitRequest(
			@RequestPayload SubmitRequestRequest submitRequestRequest,
			SoapHeader requestHeader, MessageContext messageContext)
			throws WSSecurityException, FwcmsWsException {

		logger.info("** calling submitRequest **");

		checkTokenInUserSession(messageContext);
		
		checkApplicationIdInUser(messageContext);

		SubmitRequestResponse response = new SubmitRequestResponse();
		try {
			logger.info(" submit request data=>" + submitRequestRequest.getData());
			List<SubmitRequestResult> submitRequestResults = requestService.submitRequest(submitRequestRequest.getData());
			EnumStatusResult enumPendingStatus = EnumStatusResult.PENDING;
			AfisResult afisResult = new AfisResult();
			afisResult.setModelList(submitRequestResults);
			afisResult.setRemarks(enumPendingStatus.toString());

			ObjectMapper mapper = new ObjectMapper();
			String afisResultStr = mapper.writeValueAsString(afisResult);
			response.setAfisResult(afisResultStr);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.CANNOT_PARSING_JSON);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.INCORRECT_JSON_FORMAT);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.CANNOT_READ_JSON);
		} catch (FwcmsServiceException e) {
			e.printStackTrace();
			throw new FwcmsWsException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	@PayloadRoot(localPart = GET_RESULT_REQUEST, namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	GetResultResponse getResult(
			@RequestPayload GetResultRequest getResultRequest,
			SoapHeader requestHeader, MessageContext messageContext)
			throws WSSecurityException, FwcmsWsException {

		logger.info("** calling getResult **");

		checkTokenInUserSession(messageContext);
		
		checkApplicationIdInUser(messageContext);

		GetResultResponse response = new GetResultResponse();

		try {

			GetResultResult afisResult = requestService.getResult(getResultRequest.getData());
			ObjectMapper mapper = new ObjectMapper();
			String afisResultStr = mapper.writeValueAsString(afisResult);

			response.setAfisResult(afisResultStr);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.CANNOT_PARSING_JSON);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.INCORRECT_JSON_FORMAT);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.CANNOT_READ_JSON);
		} catch (FwcmsServiceException e) {
			e.printStackTrace();
			throw new FwcmsWsException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new FwcmsWsException(FwcmsWsException.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
