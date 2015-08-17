package com.nexbis.fwcms.ws.test;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexbis.fwcms.domain.enums.EnumStatusResult;
import com.nexbis.fwcms.domain.ws.AfisResult;
import com.nexbis.fwcms.domain.ws.GetResultResult;
import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.domain.ws.request.result.GetResultRequest;
import com.nexbis.fwcms.domain.ws.request.result.GetResultResponse;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestRequest;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestResponse;
import com.nexbis.fwcms.domain.ws.user.authenticate.AuthenticateUserResponse;
import com.nexbis.fwcms.service.utils.FwcmsServiceUtil;
import com.nexbis.fwcms.ws.test.client.AuthenticateUserClient;
import com.nexbis.fwcms.ws.test.client.GetResultClient;
import com.nexbis.fwcms.ws.test.client.SubmitRequestClient;

@Test
@ContextConfiguration(locations = {"classpath:jetty-context.xml","classpath:application-ws-context-test.xml","classpath:application-ws-context-client-test.xml"})
public class RequestWsTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private static final String CORRECT_USERNAME = "user_fwcms";
	private static final String CORRECT_PASSWORD = "12345";
	private static final String CORRECT_APPLICATION_ID = "104";
	
	private String token;
	
	private String requestId;
	
	@Autowired
	private AuthenticateUserClient authenticateUserClient;
	
	@Autowired
	private SubmitRequestClient submitRequestClient;
	
	@Autowired
	private GetResultClient getResultClient;
	

	@BeforeMethod
	public void init() {
		token = authenticateAndGetToken();
		System.setProperty(FwcmsServiceUtil.FWCMS_SESSION_TIMEOUT_PROPERTY, AuthenticateUserWsTest.FWCMS_SESSION_TIMEOUT_VALUE);
	}
	
	@AfterMethod
	public void destroy(){
		
	}
	
	private String authenticateAndGetToken(){

		AuthenticateUserResponse response = authenticateUserClient.authenticateUser(CORRECT_USERNAME, CORRECT_PASSWORD, CORRECT_APPLICATION_ID);
		
		return response.getToken();
	}
	
	@Test(enabled=true)
	public void shouldReturnAfisResultWhenSubmitRequestSuccessOnSubmitRequest() throws Exception {
		SubmitRequestRequest request = new SubmitRequestRequest();
		
		String submitRequestDataStr = "{\"listCount\":\"1\",\"modelList\":[{\"xml\":\"PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/PjxyZXF1ZXN0PjxmaWVs"
			+ "ZHM+PGZpZWxkIG5hbWU9ImRvY190eXBlIj5YPC9maWVsZD48ZmllbGQgbmFtZT0iZG9jX25vIj4y"
			+ "PC9maWVsZD48ZmllbGQgbmFtZT0iZG9jX2V4cGlyeSI+MzwvZmllbGQ+PGZpZWxkIG5hbWU9Imlz"
			+ "c3VlX2F1dGhvcml0eSI+NDwvZmllbGQ+PGZpZWxkIG5hbWU9InN1cm5hbWUiPjU8L2ZpZWxkPjxm"
			+ "aWVsZCBuYW1lPSJnaXZlbm5hbWUiPjY8L2ZpZWxkPjxmaWVsZCBuYW1lPSJkYXRlb2ZiaXJ0aCI+"
			+ "NzwvZmllbGQ+PGZpZWxkIG5hbWU9Im5hdGlvbmFsaXR5Ij44PC9maWVsZD48ZmllbGQgbmFtZT0i"
			+ "Z2VuZGVyIj45PC9maWVsZD48L2ZpZWxkcz48aW1hZ2VzPjxpbWFnZSBuYW1lPSJwaG90byI+MTwv"
			+ "aW1hZ2U+PGltYWdlIG5hbWU9InJpZ2h0X3RodW1iIj4yPC9pbWFnZT48aW1hZ2UgbmFtZT0icmln"
			+ "aHRfaW5kZXgiPjM8L2ltYWdlPjxpbWFnZSBuYW1lPSJyaWdodF9taWRkbGUiPjQ8L2ltYWdlPjxp"
			+ "bWFnZSBuYW1lPSJyaWdodF9yaW5nIj41PC9pbWFnZT48aW1hZ2UgbmFtZT0icmlnaHRfbGl0dGxl"
			+ "Ij42PC9pbWFnZT48aW1hZ2UgbmFtZT0ibGVmdF90aHVtYiI+NzwvaW1hZ2U+PGltYWdlIG5hbWU9"
			+ "ImxlZnRfaW5kZXgiPjg8L2ltYWdlPjxpbWFnZSBuYW1lPSJsZWZ0X21pZGRsZSI+OTwvaW1hZ2U+"
			+ "PGltYWdlIG5hbWU9ImxlZnRfcmluZyI+MTE8L2ltYWdlPjxpbWFnZSBuYW1lPSJsZWZ0X2xpdHRs"
			+ "ZSI+MjI8L2ltYWdlPjwvaW1hZ2VzPjwvcmVxdWVzdD4=\"}]}";
		
		request.setData(submitRequestDataStr);
		
		SubmitRequestResponse response = submitRequestClient.submitRequest(request,CORRECT_USERNAME,CORRECT_PASSWORD,token,CORRECT_APPLICATION_ID);
		
		Assert.assertNotNull(response.getAfisResult());
		Assert.assertNotEquals(response.getAfisResult(),"");
		
		ObjectMapper mapper = new ObjectMapper();
		AfisResult result = mapper.readValue(response.getAfisResult(), AfisResult.class);
		
		List<SubmitRequestResult> submitRequestResults = result.getModelList();
		
		Assert.assertEquals(submitRequestResults.size(), 1);
		
		SubmitRequestResult requestResult = submitRequestResults.get(0);
		
		requestId = requestResult.getRequestId().toString();
	}
	
	@Test(enabled=true, dependsOnMethods="shouldReturnAfisResultWhenSubmitRequestSuccessOnSubmitRequest")
	public void shouldReturnAfisResultWhenGetResultSuccessOnGetResult() throws Exception {
		GetResultRequest request = new GetResultRequest();
		request.setData("{\"requestId\":\""+requestId+"\"}");
		GetResultResponse response = getResultClient.submitRequest(request, CORRECT_USERNAME, CORRECT_PASSWORD, token,CORRECT_APPLICATION_ID);
		ObjectMapper mapper = new ObjectMapper();
		GetResultResult result = mapper.readValue(response.getAfisResult(), GetResultResult.class);
		
		Assert.assertEquals(result.getRemarks(), EnumStatusResult.PENDING.toString());
	}
}
