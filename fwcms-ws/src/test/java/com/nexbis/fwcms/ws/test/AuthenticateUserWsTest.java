package com.nexbis.fwcms.ws.test;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@Test
@ContextConfiguration(locations = {"classpath:application-ws-context-test.xml","classpath:application-ws-context-client-test.xml"})
@Transactional
public class AuthenticateUserWsTest extends AbstractTestNGSpringContextTests {

	public static final String FWCMS_SESSION_TIMEOUT_VALUE = "10";
	
	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@BeforeMethod
	public void init() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}
	
	@AfterMethod
	public void destroy(){
		
	}
	
	@Test(enabled=true,dataProvider = "correctUserPassword")
	public void shouldReturnNewTokenWhenAuthenSuccessOnAuthenticateUser(String username, String password, String applicationId) throws Exception {
//		Source requestPayload = new StringSource("<ws:authenticateUserRequest xmlns:ws='http://ws.fwcms.s5.com.my/'/>");
		Source requestEnvelope = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.fwcms.s5.com.my/\"><soapenv:Header><wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken><wsse:Username>"+username+"</wsse:Username><wsse:Password>"+password+"</wsse:Password></wsse:UsernameToken></wsse:Security><ws:ApplicationId>"+applicationId+"</ws:ApplicationId></soapenv:Header><soapenv:Body><ws:authenticateUserRequest/></soapenv:Body></soapenv:Envelope>");
//		Source responsePayload = new StringSource("<ns2:authenticateUserResponse token='595ead56-321f-4f14-aa34-c0e1c0c12749' xmlns:ns2='http://ws.fwcms.s5.com.my/'/>");

		mockClient.sendRequest(withSoapEnvelope(requestEnvelope)).andExpect(noFault()).andExpect(xpath("/*[name()='ns2:authenticateUserResponse']/@token").exists());

	}
	
	@Test(enabled=true,dataProvider = "falseUserPassword")
	public void shouldReturnSoapFaultWhenAuthenFailWithUserOrPasswordOnAuthenticateUser(String username, String password, String applicationId) throws Exception {
		Source requestEnvelope = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.fwcms.s5.com.my/\"><soapenv:Header><wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken><wsse:Username>"+username+"</wsse:Username><wsse:Password>"+password+"</wsse:Password></wsse:UsernameToken></wsse:Security><ws:ApplicationId>"+applicationId+"</ws:ApplicationId></soapenv:Header><soapenv:Body><ws:authenticateUserRequest/></soapenv:Body></soapenv:Envelope>");

		mockClient.sendRequest(withSoapEnvelope(requestEnvelope)).andExpect(clientOrSenderFault());

	}
	
	@Test(enabled=true,dataProvider = "falseApplicationId")
	public void shouldReturnSoapFaultWhenAuthenFailWithApplicationIdOnAuthenticateUser(String username, String password, String applicationId) throws Exception {
		Source requestEnvelope = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.fwcms.s5.com.my/\"><soapenv:Header><wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken><wsse:Username>"+username+"</wsse:Username><wsse:Password>"+password+"</wsse:Password></wsse:UsernameToken></wsse:Security><ws:ApplicationId>"+applicationId+"</ws:ApplicationId></soapenv:Header><soapenv:Body><ws:authenticateUserRequest/></soapenv:Body></soapenv:Envelope>");

		mockClient.sendRequest(withSoapEnvelope(requestEnvelope)).andExpect(serverOrReceiverFault());

	}
	
	@DataProvider(name = "correctUserPassword")
	public Object[][] provideCorrectUserPassword() {
 
		return new Object[][] { 
			{ "user_fwcms", "12345", "104" }
		};
	}
	
	@DataProvider(name = "falseUserPassword")
	public Object[][] provideFalseUserPassword() {
 
		return new Object[][] { 
			{ "user_fwcms", "123", "104" },
			{ "NoBody", "1","104" },
		};
	}
	
	@DataProvider(name = "falseApplicationId")
	public Object[][] provideFalseApplicationId() {
 
		return new Object[][] { 
			{ "user_fwcms", "12345", "1" }
		};
	}
}
