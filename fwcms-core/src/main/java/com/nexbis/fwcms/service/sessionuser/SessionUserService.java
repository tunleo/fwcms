package com.nexbis.fwcms.service.sessionuser;

import com.nexbis.fwcms.domain.SessionUser;

public interface SessionUserService {

	public SessionUser authenticateUser();
	
	public boolean isUserPassedAuthentication(String token);
	
	public SessionUser extendSessionUser();
}
