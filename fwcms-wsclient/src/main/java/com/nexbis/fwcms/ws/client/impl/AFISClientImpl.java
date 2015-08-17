package com.nexbis.fwcms.ws.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class AFISClientImpl {

	@Autowired
	@Qualifier("customerInfoInquiryTemplate")
    private WebServiceTemplate wsTemplate;
	
}
