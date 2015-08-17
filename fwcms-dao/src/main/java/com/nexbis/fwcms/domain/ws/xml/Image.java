package com.nexbis.fwcms.domain.ws.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

@XmlAccessorType(XmlAccessType.FIELD)
public class Image extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220942722790597441L;
	
	@XmlPath("image[@name='photo']/text()")
	private String photo;
	
	@XmlPath("image[@name='right_thumb']/text()")
	private String rightThumb;
	
	@XmlPath("image[@name='right_index']/text()")
	private String rightIndex;
	
	@XmlPath("image[@name='right_middle']/text()")
	private String rightMiddle;
	
	@XmlPath("image[@name='right_ring']/text()")
	private String rightRing;
	
	@XmlPath("image[@name='right_little']/text()")
	private String rightLittle;
	
	@XmlPath("image[@name='left_thumb']/text()")
	private String leftThumb;
	
	@XmlPath("image[@name='left_index']/text()")
	private String leftIndex;
	
	@XmlPath("image[@name='left_middle']/text()")
	private String leftMiddle;
	
	@XmlPath("image[@name='left_ring']/text()")
	private String leftRing;
	
	@XmlPath("image[@name='left_little']/text()")
	private String leftLittle;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRightThumb() {
		return rightThumb;
	}

	public void setRightThumb(String rightThumb) {
		this.rightThumb = rightThumb;
	}

	public String getRightIndex() {
		return rightIndex;
	}

	public void setRightIndex(String rightIndex) {
		this.rightIndex = rightIndex;
	}

	public String getRightMiddle() {
		return rightMiddle;
	}

	public void setRightMiddle(String rightMiddle) {
		this.rightMiddle = rightMiddle;
	}

	public String getRightRing() {
		return rightRing;
	}

	public void setRightRing(String rightRing) {
		this.rightRing = rightRing;
	}

	public String getRightLittle() {
		return rightLittle;
	}

	public void setRightLittle(String rightLittle) {
		this.rightLittle = rightLittle;
	}

	public String getLeftThumb() {
		return leftThumb;
	}

	public void setLeftThumb(String leftThumb) {
		this.leftThumb = leftThumb;
	}

	public String getLeftIndex() {
		return leftIndex;
	}

	public void setLeftIndex(String leftIndex) {
		this.leftIndex = leftIndex;
	}

	public String getLeftMiddle() {
		return leftMiddle;
	}

	public void setLeftMiddle(String leftMiddle) {
		this.leftMiddle = leftMiddle;
	}

	public String getLeftRing() {
		return leftRing;
	}

	public void setLeftRing(String leftRing) {
		this.leftRing = leftRing;
	}

	public String getLeftLittle() {
		return leftLittle;
	}

	public void setLeftLittle(String leftLittle) {
		this.leftLittle = leftLittle;
	}
	
	
}
