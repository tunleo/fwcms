package com.nexbis.fwcms.service.common;

import com.nexbis.fwcms.domain.User;
import com.nexbis.fwcms.service.utils.FwcmsServiceUtil;

public abstract class AbstractService {
	
	protected User getCurrentUser(){
		return FwcmsServiceUtil.getCurrentUser();
	}
	
	protected String getSessionTimeoutFromProperty(){
		return  FwcmsServiceUtil.getSessionTimeoutFromProperty();
	}
}
