package com.nexbis.fwcms.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.dao.request.RequestDAO;
import com.nexbis.fwcms.domain.ws.xml.SubmitRequestData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao-context-test.xml"})
@Transactional
public class RequestDAOTest {
	
	@Autowired
	private RequestDAO requestDAO;
	
	private String requestId;
	
	@BeforeTransaction
	public void beforeTransaction(){
	}
	
	@AfterTransaction
	public void afterTransaction(){
	}
	
	@Test
	public void shouldReturnRequestIdWhenSubmitRequestDataOnInsertSubmitRequest() throws Exception{
		SubmitRequestData submitRequest = new SubmitRequestData();
		submitRequest.setRequestId("request-id");
		Assert.assertNotNull(submitRequest.getRequestId());
		requestDAO.insertSubmitRequest(submitRequest);
		Assert.assertNotNull(submitRequest.getRequestId());
		
		requestId = submitRequest.getRequestId();
		
		SubmitRequestData submitRequestData = requestDAO.selectSubmitRequestByRequestId(requestId);
		
		Assert.assertNotNull(submitRequestData);
	}

}
