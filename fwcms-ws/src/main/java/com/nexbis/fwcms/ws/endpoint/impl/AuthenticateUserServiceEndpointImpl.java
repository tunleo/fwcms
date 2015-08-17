package com.nexbis.fwcms.ws.endpoint.impl;

import org.apache.ws.security.WSSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

import com.nexbis.fwcms.domain.SessionUser;
import com.nexbis.fwcms.domain.ws.user.authenticate.AuthenticateUserResponse;
import com.nexbis.fwcms.ws.endpoint.AuthenticateUserServiceEndpoint;
import com.nexbis.fwcms.ws.endpoint.common.AbstractServiceEndpoint;

@Endpoint
public class AuthenticateUserServiceEndpointImpl extends AbstractServiceEndpoint implements AuthenticateUserServiceEndpoint {
	
	private static Logger logger = LoggerFactory.getLogger(AuthenticateUserServiceEndpointImpl.class);

	@PayloadRoot(localPart = AUTHENTICATE_USER_REQUEST, namespace = TARGET_NAMESPACE)
	public @ResponsePayload AuthenticateUserResponse authenticateUser(SoapHeader requestHeader,MessageContext messageContext) throws WSSecurityException{
		logger.info(" ** calling authenticateUser ** ");
		AuthenticateUserResponse authenticateUserResponse = new AuthenticateUserResponse();
		checkApplicationIdInUser(messageContext);
		try{
			SessionUser sessionUser = sessionUserService.authenticateUser();
			
			authenticateUserResponse.setToken(sessionUser.getToken());
		}catch(Exception e){
			// Ignore exception, if exception occur authenticateUser will be null, program will handle it.
			e.printStackTrace();
		}
		
		return authenticateUserResponse;
	}
}
