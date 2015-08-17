package com.nexbis.fwcms.ws.endpoint.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class FwcmsWsException extends Exception {
	
	public static final String CANNOT_PARSING_JSON = "Incorrect output data in JSON : error parsing JSON data.";
	public static final String INCORRECT_JSON_FORMAT = "Incorrect output data in JSON : incorrect JSON format.";
	public static final String CANNOT_READ_JSON = "Incorrect output data in JSON : cannot read JSON data.";
	public static final String INTERNAL_SERVER_ERROR = "Internal server error.";
	/**
	 * 
	 */
	private static final long serialVersionUID = 7823554205471000294L;
	
	public FwcmsWsException(){
		
	}
	
	public FwcmsWsException(String msg){
		super(msg);
	}
}
