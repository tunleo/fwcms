package com.nexbis.fwcms.service.sessionuser.impl;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.dao.user.SessionUserDAO;
import com.nexbis.fwcms.domain.SessionUser;
import com.nexbis.fwcms.domain.User;
import com.nexbis.fwcms.service.common.AbstractService;
import com.nexbis.fwcms.service.sessionuser.SessionUserService;
import com.nexbis.fwcms.service.utils.FwcmsServiceUtil;

@Service
public class SessionUserServiceImpl extends AbstractService implements SessionUserService{
	
	private static Logger logger = LoggerFactory.getLogger(SessionUserServiceImpl.class);
	
	@Autowired
	private SessionUserDAO sessionUserDAO;
	
	@Transactional
	@Override
	public SessionUser authenticateUser(){
		User user = getCurrentUser();
		
		SessionUser sessionUser = sessionUserDAO.selectSessionUserByUserName(user.getUsername());
		
		if(sessionUser == null){
			sessionUser = new SessionUser(user.getUsername(),FwcmsServiceUtil.generateUUID(),FwcmsServiceUtil.getCurrentTimestamp());
			sessionUserDAO.insertSessionUser(sessionUser);
		} else {
			sessionUser.setAuthenticationDateTime(FwcmsServiceUtil.getCurrentTimestamp());
			sessionUser.setToken(FwcmsServiceUtil.generateUUID());
			int updateRecords = sessionUserDAO.updateSessionUserByUsername(sessionUser);
			
			logger.info("No. record updateSessionUserByUsername => "+updateRecords);
		}
		
		return sessionUser;
	}
	
	@Transactional(readOnly=true)
	@Override
	public boolean isUserPassedAuthentication(String token){
		User user = getCurrentUser();
		
		SessionUser sessionUser = sessionUserDAO.selectSessionUserByUserName(user.getUsername());
		if(sessionUser == null){
			return false;
		}
		if((token == null) || !token.equals(sessionUser.getToken())){
			return false;
		}
		
		Date authenticationUtilDateTime = new Date(sessionUser.getAuthenticationDateTime().getTime());
		Date currentUtilDateTime = new Date(FwcmsServiceUtil.getCurrentTimestamp().getTime());
		String sessionTimeoutStr = getSessionTimeoutFromProperty();

		int sessionTimeout = Integer.parseInt(sessionTimeoutStr);
		Date currentUtilDateTimeMinusSessionTime = DateUtils.addMinutes(currentUtilDateTime, -sessionTimeout);
		
		if(authenticationUtilDateTime.before(currentUtilDateTimeMinusSessionTime)){
			return false;
		}
		
		return true;
	}
	
	@Transactional
	@Override
	public SessionUser extendSessionUser(){
		User user = getCurrentUser();
		
		SessionUser sessionUser = sessionUserDAO.selectSessionUserByUserName(user.getUsername());
		sessionUser.setAuthenticationDateTime(FwcmsServiceUtil.getCurrentTimestamp());
		int updateRecords = sessionUserDAO.updateSessionUserByUsername(sessionUser);
		
		logger.info("No. record updateSessionUserByUsername => "+updateRecords);
		
		return sessionUser;
	}
}
