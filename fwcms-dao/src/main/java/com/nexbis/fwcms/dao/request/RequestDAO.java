package com.nexbis.fwcms.dao.request;

import java.sql.Timestamp;
import java.util.List;

import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.domain.ws.xml.SubmitRequestData;

public interface RequestDAO {
	public void insertSubmitRequest(SubmitRequestData submitRequest);
	
	public List<SubmitRequestResult> insertSubmitRequest(List<SubmitRequestData> submitRequests);
	
	public SubmitRequestData selectSubmitRequestByRequestId(String requestId);
	
	public int updateGetResultDateTimeByRequestId(String requestId, Timestamp getResultDateime);
}
