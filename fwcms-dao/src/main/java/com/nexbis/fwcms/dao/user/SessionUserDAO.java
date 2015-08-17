package com.nexbis.fwcms.dao.user;

import com.nexbis.fwcms.domain.SessionUser;

public interface SessionUserDAO {
	public void insertSessionUser(SessionUser sessionUser);
	
	public SessionUser selectSessionUserByUserName(String username);
	
	public int updateSessionUserByUsername(SessionUser sessionUser);
}
