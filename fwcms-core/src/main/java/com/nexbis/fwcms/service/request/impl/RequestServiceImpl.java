package com.nexbis.fwcms.service.request.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.ws.security.WSSecurityException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.dao.request.RequestDAO;
import com.nexbis.fwcms.domain.enums.EnumStatusResult;
import com.nexbis.fwcms.domain.ws.Data;
import com.nexbis.fwcms.domain.ws.GetResultData;
import com.nexbis.fwcms.domain.ws.GetResultResult;
import com.nexbis.fwcms.domain.ws.Model;
import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.domain.ws.xml.SubmitRequestData;
import com.nexbis.fwcms.service.common.AbstractService;
import com.nexbis.fwcms.service.exception.FwcmsServiceException;
import com.nexbis.fwcms.service.request.RequestService;
import com.nexbis.fwcms.service.sessionuser.SessionUserService;
import com.nexbis.fwcms.service.utils.FwcmsServiceUtil;

@Service
public class RequestServiceImpl extends AbstractService implements RequestService {
	
	private static Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private SessionUserService sessionUserService;
	
	@Transactional
	@Override
	public List<SubmitRequestResult> submitRequest(String dataStr) throws FwcmsServiceException,WSSecurityException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			logger.info("submitRequest dataStr=>"+dataStr);
			Data data = mapper.readValue(dataStr, Data.class);
			Integer listCount = data.getListCount();
			List<Model> modelList = data.getModelList();
			
			if(listCount == null || listCount != modelList.size()){
				throw new FwcmsServiceException(FwcmsServiceException.LIST_COUNT_N_MODEL_NOT_EQUAL);
			}
			
			List<SubmitRequestData> submitRequests = convertModelsToSubmitRequests(modelList);
			logger.info("submitRequest submitRequests=>"+submitRequests);
			List<SubmitRequestResult> requestResults = requestDAO.insertSubmitRequest(submitRequests);
			
			return requestResults;
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.CANNOT_PARSING_JSON);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.INCORRECT_JSON_FORMAT);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.CANNOT_READ_JSON);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.INCORRECT_XML_FORMAT);
		} catch (FwcmsServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Transactional
	@Override
	public GetResultResult getResult(String dataStr) throws FwcmsServiceException {
		try{
			logger.info("getResult data =>"+dataStr);
			
			ObjectMapper mapper = new ObjectMapper();
			GetResultData data = mapper.readValue(dataStr, GetResultData.class);
			
			int updateGetResultRecord = requestDAO.updateGetResultDateTimeByRequestId(data.getRequestId(), FwcmsServiceUtil.getCurrentTimestamp());
			
			logger.info("No. records updateGetResultDateTimeByRequestId => "+updateGetResultRecord);
			
			SubmitRequestData submitRequest = requestDAO.selectSubmitRequestByRequestId(data.getRequestId());
			
			logger.info("getResult submitRequest=>"+submitRequest);
			
			if(submitRequest == null){
				throw new FwcmsServiceException(FwcmsServiceException.INVALID_REQUEST_ID);
			}
			
			GetResultResult getResultResult = new GetResultResult();
			
			getResultResult.setStatusCode(submitRequest.getStatusResult());
			
			getResultResult.setRemarks(EnumStatusResult.getByStatusResultCode(submitRequest.getStatusResult()).toString());
			
			return getResultResult;
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.CANNOT_PARSING_JSON);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.INCORRECT_JSON_FORMAT);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.CANNOT_READ_JSON);
		} catch (FwcmsServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FwcmsServiceException(FwcmsServiceException.INTERNAL_SERVER_ERROR);
		}
	}
	
	private List<SubmitRequestData> convertModelsToSubmitRequests(List<Model> models) throws JAXBException, IOException{
		if(models != null && models.size() > 0){
			List<SubmitRequestData> submitRequests = new ArrayList<SubmitRequestData>();
			JAXBContext jaxbContext = JAXBContext.newInstance(SubmitRequestData.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			for(Model model : models){
				String submitRequestStr = model.getXml();
				logger.info("submitRequest input xml=>"+submitRequestStr);
				String submitRequestStrXmlFormat = new String(Base64.decodeBase64(submitRequestStr));
				logger.info("submitRequest input xml after decode=>"+submitRequestStrXmlFormat);
				InputStream submitRequestIs = new ByteArrayInputStream(submitRequestStrXmlFormat.getBytes());
				
				//Create submitRequestData from XML
				SubmitRequestData submitRequest = (SubmitRequestData)unmarshaller.unmarshal(submitRequestIs);
				//Add UUID to submitRequestData
				submitRequest.setRequestId(FwcmsServiceUtil.generateUUID());
				
				submitRequest.setStatusResult(EnumStatusResult.PENDING.getStatusResultCode());
				submitRequest.setDateTimeRequest(FwcmsServiceUtil.getCurrentTimestamp());
				submitRequestIs.close();
				submitRequests.add(submitRequest);
			}
			
			return submitRequests;
		}
		
		return Collections.emptyList();
		
	}
}
