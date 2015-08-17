package com.nexbis.fwcms.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.domain.enums.EnumStatusResult;
import com.nexbis.fwcms.domain.ws.GetResultResult;
import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.service.request.RequestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-core-context-test.xml"})
@Transactional
public class RequestServiceTest {
	
	@Autowired
	private RequestService requestService;
	
	@Test
	public void shouldReturnSubmitRequestResultWhenSubmitDataOnSubmitRequest() throws Exception{
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
		
		List<SubmitRequestResult> requestResults = requestService.submitRequest(submitRequestDataStr);

		Assert.assertNotNull(requestResults);
		
		Assert.assertTrue(requestResults.size()==1);
		
		SubmitRequestResult requestResult = requestResults.get(0);
		
		GetResultResult getResultResult = requestService.getResult("{\"requestId\":\""+requestResult.getRequestId()+"\"}");

		Assert.assertNotNull(getResultResult);
		
		Assert.assertEquals(EnumStatusResult.PENDING.toString(), getResultResult.getRemarks());
	}
	
}
