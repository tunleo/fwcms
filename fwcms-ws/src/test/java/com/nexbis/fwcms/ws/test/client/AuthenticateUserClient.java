package com.nexbis.fwcms.ws.test.client;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import com.nexbis.fwcms.domain.ws.user.authenticate.AuthenticateUserRequest;
import com.nexbis.fwcms.domain.ws.user.authenticate.AuthenticateUserResponse;
import com.nexbis.fwcms.ws.endpoint.RequestServiceEndpoint;

@Component
public class AuthenticateUserClient extends AbstractWsClient{
	
	@Autowired
	@Qualifier("authenticateUserTemplate")
    private WebServiceTemplate wsTemplate;
	
	public AuthenticateUserResponse authenticateUser(final String username, final String password,final String applicationId){
		AuthenticateUserResponse response = (AuthenticateUserResponse) wsTemplate.marshalSendAndReceive(new AuthenticateUserRequest(), new WebServiceMessageCallback() {
			
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException,
					TransformerException {
				try {
                    SoapMessage soapMessage = (SoapMessage)message;
                    SoapHeader header = soapMessage.getSoapHeader();
                    header.addHeaderElement(new QName(RequestServiceEndpoint.TARGET_NAMESPACE, RequestServiceEndpoint.TAG_APPLICATION_ID, RequestServiceEndpoint.PREFIX_TARGET_NAMESPACE)).setText(applicationId);
                    StringSource headerSource = new StringSource(getSecurityHeaderTag(username, password));
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(headerSource, header.getResult());

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new TransformerException(e);
                }
				
			}
		});
		
		return response;
	}

}
