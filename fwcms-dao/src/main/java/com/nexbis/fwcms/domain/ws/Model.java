package com.nexbis.fwcms.domain.ws;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class Model extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5786278008182791533L;
	
	public Model(){}
	
	public Model(String xml){
		this.xml = xml;
	}
	
	private String xml;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	
	
}
