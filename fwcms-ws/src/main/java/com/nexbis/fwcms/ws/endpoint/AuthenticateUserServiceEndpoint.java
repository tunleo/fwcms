package com.nexbis.fwcms.ws.endpoint;

import org.apache.ws.security.WSSecurityException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;

import com.nexbis.fwcms.domain.ws.user.authenticate.AuthenticateUserResponse;
import com.nexbis.fwcms.ws.endpoint.common.BaseServiceEndpoint;


public interface AuthenticateUserServiceEndpoint extends BaseServiceEndpoint {

	public AuthenticateUserResponse authenticateUser(SoapHeader requestHeader,MessageContext messageContext) throws WSSecurityException;
}
