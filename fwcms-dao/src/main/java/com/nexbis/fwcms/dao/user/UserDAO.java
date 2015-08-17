package com.nexbis.fwcms.dao.user;

import com.nexbis.fwcms.domain.User;

public interface UserDAO {
	
	public User selectUserByUserName(String userName);
	

}
