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

import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestRequest;
import com.nexbis.fwcms.domain.ws.request.submit.SubmitRequestResponse;
import com.nexbis.fwcms.ws.endpoint.RequestServiceEndpoint;

@Component
public class SubmitRequestClient extends AbstractWsClient {
	
	@Autowired
	@Qualifier("submitRequestTemplate")
    private WebServiceTemplate wsTemplate;
	
	public SubmitRequestResponse submitRequest(SubmitRequestRequest request,final String username, final String password, final String token, final String applicationId){
		SubmitRequestResponse response = (SubmitRequestResponse) wsTemplate.marshalSendAndReceive(request, new WebServiceMessageCallback() {
			
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException,
					TransformerException {
				try {
                    SoapMessage soapMessage = (SoapMessage)message;
                    SoapHeader header = soapMessage.getSoapHeader();
                    header.addHeaderElement(new QName(RequestServiceEndpoint.TARGET_NAMESPACE, RequestServiceEndpoint.TAG_AUTHENTICATED_TOKEN, RequestServiceEndpoint.PREFIX_TARGET_NAMESPACE)).setText(token);
                    header.addHeaderElement(new QName(RequestServiceEndpoint.TARGET_NAMESPACE, RequestServiceEndpoint.TAG_APPLICATION_ID, RequestServiceEndpoint.PREFIX_TARGET_NAMESPACE)).setText(applicationId);
                    String headerWithToken = getSecurityHeaderTag(username, password);

                    StringSource headerSource = new StringSource(headerWithToken);
                    
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
