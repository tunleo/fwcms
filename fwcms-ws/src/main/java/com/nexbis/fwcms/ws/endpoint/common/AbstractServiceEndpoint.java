package com.nexbis.fwcms.ws.endpoint.common;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.ws.security.WSSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.nexbis.fwcms.domain.User;
import com.nexbis.fwcms.service.sessionuser.SessionUserService;
import com.nexbis.fwcms.service.user.UserService;
import com.nexbis.fwcms.service.utils.FwcmsServiceUtil;

public abstract class AbstractServiceEndpoint implements BaseServiceEndpoint {
	private static Logger logger = LoggerFactory.getLogger(AbstractServiceEndpoint.class);
	
	@Autowired
	protected SessionUserService sessionUserService;
	
	@Qualifier("userDetailsService")
	@Autowired
	private UserService userService;
	
	protected String getClientTokenValue(MessageContext messageContext) throws WSSecurityException {
		return getHeaderValueByTagName(messageContext, TARGET_NAMESPACE, TAG_AUTHENTICATED_TOKEN);
		
	}
	
	protected String getClientAppIdValue(MessageContext messageContext) throws WSSecurityException {
		return getHeaderValueByTagName(messageContext, TARGET_NAMESPACE, TAG_APPLICATION_ID);
		
	}
	
	protected User getCurrentUser(){
		return FwcmsServiceUtil.getCurrentUser();
	}
	
	protected String getHeaderValueByTagName(MessageContext messageContext,String targetNamespace,String tagName) throws WSSecurityException {
		logger.debug("** tagName => "+tagName+" **");
		SaajSoapMessage soapRequest = (SaajSoapMessage) messageContext.getRequest();
		SoapEnvelope reqEnvelope = soapRequest.getEnvelope();
		SoapHeader rqheader = reqEnvelope.getHeader();
		Iterator<SoapHeaderElement> tokenElements = rqheader.examineHeaderElements(new QName(targetNamespace, tagName));
		
		logger.debug("tokenElements != null ? => "+(tokenElements !=null));
		
		if(tokenElements == null){
			throw new WSSecurityException(tagName + " is required");
		}
		
		String tagValue = "";
		int countElement = 0;
		while(tokenElements.hasNext()){
			++countElement;
			SoapHeaderElement soapHeaderElement = tokenElements.next();
			tagValue = soapHeaderElement.getText();
		}
		
		logger.debug("** countElement => "+countElement+" **");
		
		if(countElement != 1){
			throw new WSSecurityException(tagName + " must have one value.");
		}
		
		logger.debug("** tagValue => "+tagValue+" **");
		
		return tagValue;
	}
	
	protected void checkTokenInUserSession(MessageContext messageContext) throws WSSecurityException{
		String token = getClientTokenValue(messageContext);
		
		if(!sessionUserService.isUserPassedAuthentication(token)){
			throw new WSSecurityException(WSSecurityException.INVALID_SECURITY_TOKEN);
		} else {
			sessionUserService.extendSessionUser();
		}
	}
	
	protected void checkApplicationIdInUser(MessageContext messageContext) throws WSSecurityException{
		String clientApplicationId = getClientAppIdValue(messageContext);
		
		User currentUser = (User) userService.loadUserByUsername(getCurrentUser().getUsername());
		
		if(!clientApplicationId.equals(currentUser.getApplicationId())){
			throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION);
		}
	}
}
