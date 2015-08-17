package com.nexbis.fwcms.ws.test.client;

public abstract class AbstractWsClient {
	protected String getSecurityHeaderTag(final String username, final String password){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n");
		stringBuilder.append("<wsse:UsernameToken>\n");
		stringBuilder.append("   <wsse:Username>");
		stringBuilder.append(username);
		stringBuilder.append("</wsse:Username>\n");
		stringBuilder.append("   <wsse:Password>");
		stringBuilder.append(password);
		stringBuilder.append("</wsse:Password>\n");
		stringBuilder.append("</wsse:UsernameToken>\n");
		stringBuilder.append("</wsse:Security>");
		
		return stringBuilder.toString();
	}
}
