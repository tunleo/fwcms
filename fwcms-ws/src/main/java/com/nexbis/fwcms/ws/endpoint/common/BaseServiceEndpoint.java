package com.nexbis.fwcms.ws.endpoint.common;

public interface BaseServiceEndpoint {
	public static final String TAG_AUTHENTICATED_TOKEN = "AuthenticatedToken";
	public static final String TAG_APPLICATION_ID = "ApplicationId";
	public static final String TARGET_NAMESPACE = "http://ws.fwcms.s5.com.my/";
	public static final String PREFIX_TARGET_NAMESPACE = "ws";
	
	public static final String AUTHENTICATE_USER_REQUEST = "authenticateUserRequest";
	public static final String SUBMIT_REQUEST_REQUEST = "submitRequestRequest";
	public static final String GET_RESULT_REQUEST = "getResultRequest";
	
}
