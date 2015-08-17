package com.nexbis.fwcms.domain;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class HitRequestInfo extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6118470878557772322L;
	
	private String uuId;
	private String hitId;
	private String xmlFileName;
	private String xml;
	
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getHitId() {
		return hitId;
	}
	public void setHitId(String hitId) {
		this.hitId = hitId;
	}
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	
}
