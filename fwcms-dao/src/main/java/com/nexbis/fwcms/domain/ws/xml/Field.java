package com.nexbis.fwcms.domain.ws.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

@XmlAccessorType(XmlAccessType.FIELD)
public class Field extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6616858773691182971L;

	@XmlPath("field[@name='doc_type']/text()")
	private String docType;
	
	@XmlPath("field[@name='doc_no']/text()")
	private String docNo;
	
	@XmlPath("field[@name='doc_expiry']/text()")
	private String docExpiry;
	
	@XmlPath("field[@name='issue_authority']/text()")
	private String issueAuthority;
	
	@XmlPath("field[@name='surname']/text()")
	private String surName;
	
	@XmlPath("field[@name='givenname']/text()")
	private String givenName;
	
	@XmlPath("field[@name='dateofbirth']/text()")
	private String dateOfBirth;
	
	@XmlPath("field[@name='nationality']/text()")
	private String nationality;
	
	@XmlPath("field[@name='gender']/text()")
	private String gender;
	
	@XmlPath("field[@name='entry_date']/text()")
	private String entryDate;
	
	@XmlPath("field[@name='entry_expiry']/text()")
	private String entryExpiry;
	
	@XmlPath("field[@name='opt_data1']/text()")
	private String optionData1;
	
	@XmlPath("field[@name='opt_data2']/text()")
	private String optionData2;
	
	@XmlPath("field[@name='remarks']/text()")
	private String remarks;

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocExpiry() {
		return docExpiry;
	}

	public void setDocExpiry(String docExpiry) {
		this.docExpiry = docExpiry;
	}

	public String getIssueAuthority() {
		return issueAuthority;
	}

	public void setIssueAuthority(String issueAuthority) {
		this.issueAuthority = issueAuthority;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryExpiry() {
		return entryExpiry;
	}

	public void setEntryExpiry(String entryExpiry) {
		this.entryExpiry = entryExpiry;
	}

	public String getOptionData1() {
		return optionData1;
	}

	public void setOptionData1(String optionData1) {
		this.optionData1 = optionData1;
	}

	public String getOptionData2() {
		return optionData2;
	}

	public void setOptionData2(String optionData2) {
		this.optionData2 = optionData2;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
