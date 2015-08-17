package com.nexbis.fwcms.service.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class FwcmsServiceException extends Exception {

	public static final String CANNOT_PARSING_JSON = "Incorrect output data in JSON : error parsing JSON data.";
	public static final String INCORRECT_JSON_FORMAT = "Incorrect output data in JSON : incorrect JSON format.";
	public static final String INCORRECT_XML_FORMAT = "Incorrect input data in XML : incorrect XML format.";
	public static final String CANNOT_READ_JSON = "Incorrect output data in JSON : cannot read JSON data.";
	public static final String INTERNAL_SERVER_ERROR = "Internal server error.";
	public static final String INVALID_REQUEST_ID = "Invalid request Id";
	public static final String LIST_COUNT_N_MODEL_NOT_EQUAL = "listCount and model list size are not equal.";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3332279079651032087L;
	
	public FwcmsServiceException(){
		
	}
	
	public FwcmsServiceException(String msg){
		super(msg);
	}
}
