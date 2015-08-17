package com.nexbis.fwcms.domain.ws.xml;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmitRequestData extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1643241790499830755L;

	@XmlTransient
	private String requestId;

	@XmlTransient
	private String submitRemarks;

	@XmlTransient
	private String statusCode;

	@XmlTransient
	private String getResultRemarks;

	@XmlTransient
	private String statusResult;

	@XmlTransient
	private Timestamp dateTimeProcess;

	@XmlTransient
	private Timestamp dateTimeSubmit;

	@XmlTransient
	private Timestamp dateTimeRequest;

	@XmlTransient
	private Timestamp dateTimeGetResult;

	@XmlElement(name = "fields")
	private Field field;
	@XmlElement(name = "images")
	private Image image;

	public Field getField() {
		if (field == null) {
			field = new Field();
		}
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Image getImage() {
		if (image == null) {
			image = new Image();
		}
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getDocType() {
		return getField().getDocType();
	}

	public void setDocType(String docType) {
		getField().setDocType(docType);
	}

	public String getDocNo() {
		return getField().getDocNo();
	}

	public void setDocNo(String docNo) {
		getField().setDocNo(docNo);
	}

	public String getDocExpiry() {
		return getField().getDocExpiry();
	}

	public void setDocExpiry(String docExpiry) {
		getField().setDocExpiry(docExpiry);
	}

	public String getIssueAuthority() {
		return getField().getIssueAuthority();
	}

	public void setIssueAuthority(String issueAuthority) {
		getField().setIssueAuthority(issueAuthority);
	}

	public String getSurName() {
		return getField().getSurName();
	}

	public void setSurName(String surName) {
		getField().setSurName(surName);
	}

	public String getGivenName() {
		return getField().getGivenName();
	}

	public void setGivenName(String givenName) {
		getField().setGivenName(givenName);
	}

	public String getDateOfBirth() {
		return getField().getDateOfBirth();
	}

	public void setDateOfBirth(String dateOfBirth) {
		getField().setDateOfBirth(dateOfBirth);
	}

	public String getNationality() {
		return getField().getNationality();
	}

	public void setNationality(String nationality) {
		getField().setNationality(nationality);
	}

	public String getGender() {
		return getField().getGender();
	}

	public void setGender(String gender) {
		getField().setGender(gender);
	}

	public String getEntryDate() {
		return getField().getEntryDate();
	}

	public void setEntryDate(String entryDate) {
		getField().setEntryDate(entryDate);
	}

	public String getEntryExpiry() {
		return getField().getEntryExpiry();
	}

	public void setEntryExpiry(String entryExpiry) {
		getField().setEntryExpiry(entryExpiry);
	}

	public String getOptData1() {
		return getField().getOptionData1();
	}

	public void setOptData1(String optData1) {
		getField().setOptionData1(optData1);
	}

	public String getOptData2() {
		return getField().getOptionData2();
	}

	public void setOptData2(String optData2) {
		getField().setOptionData2(optData2);
	}

	public String getRemarks() {
		return getField().getRemarks();
	}

	public void setRemarks(String remarks) {
		getField().setRemarks(remarks);
	}

	public String getPhoto() {
		return getImage().getPhoto();
	}

	public void setPhoto(String photo) {
		getImage().setPhoto(photo);
	}

	public String getRightThumb() {
		return getImage().getRightThumb();
	}

	public void setRightThumb(String rightThumb) {
		getImage().setRightThumb(rightThumb);
	}

	public String getRightIndex() {
		return getImage().getRightIndex();
	}

	public void setRightIndex(String rightIndex) {
		getImage().setRightIndex(rightIndex);
	}

	public String getRightMiddle() {
		return getImage().getRightMiddle();
	}

	public void setRightMiddle(String rightMiddle) {
		getImage().setRightMiddle(rightMiddle);
	}

	public String getRightRing() {
		return getImage().getRightRing();
	}

	public void setRightRing(String rightRing) {
		getImage().setRightRing(rightRing);
	}

	public String getRightLittle() {
		return getImage().getRightLittle();
	}

	public void setRightLittle(String rightLittle) {
		getImage().setRightLittle(rightLittle);
	}

	public String getLeftThumb() {
		return getImage().getLeftThumb();
	}

	public void setLeftThumb(String leftThumb) {
		getImage().setLeftThumb(leftThumb);
	}

	public String getLeftIndex() {
		return getImage().getLeftIndex();
	}

	public void setLeftIndex(String leftIndex) {
		getImage().setLeftIndex(leftIndex);
	}

	public String getLeftMiddle() {
		return getImage().getLeftMiddle();
	}

	public void setLeftMiddle(String leftMiddle) {
		getImage().setLeftMiddle(leftMiddle);
	}

	public String getLeftRing() {
		return getImage().getLeftRing();
	}

	public void setLeftRing(String leftRing) {
		getImage().setLeftRing(leftRing);
	}

	public String getLeftLittle() {
		return getImage().getLeftLittle();
	}

	public void setLeftLittle(String leftLittle) {
		getImage().setLeftLittle(leftLittle);
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSubmitRemarks() {
		return submitRemarks;
	}

	public void setSubmitRemarks(String submitRemarks) {
		this.submitRemarks = submitRemarks;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getGetResultRemarks() {
		return getResultRemarks;
	}

	public void setGetResultRemarks(String getResultRemarks) {
		this.getResultRemarks = getResultRemarks;
	}

	public String getStatusResult() {
		return statusResult;
	}

	public void setStatusResult(String statusResult) {
		this.statusResult = statusResult;
	}

	public Timestamp getDateTimeProcess() {
		return dateTimeProcess;
	}

	public void setDateTimeProcess(Timestamp dateTimeProcess) {
		this.dateTimeProcess = dateTimeProcess;
	}

	public Timestamp getDateTimeSubmit() {
		return dateTimeSubmit;
	}

	public void setDateTimeSubmit(Timestamp dateTimeSubmit) {
		this.dateTimeSubmit = dateTimeSubmit;
	}

	public Timestamp getDateTimeRequest() {
		return dateTimeRequest;
	}

	public void setDateTimeRequest(Timestamp dateTimeRequest) {
		this.dateTimeRequest = dateTimeRequest;
	}

	public Timestamp getDateTimeGetResult() {
		return dateTimeGetResult;
	}

	public void setDateTimeGetResult(Timestamp dateTimeGetResult) {
		this.dateTimeGetResult = dateTimeGetResult;
	}

}
