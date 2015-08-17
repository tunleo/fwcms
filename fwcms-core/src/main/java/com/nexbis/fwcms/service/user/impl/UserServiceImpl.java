package com.nexbis.fwcms.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.dao.user.UserDAO;
import com.nexbis.fwcms.domain.User;
import com.nexbis.fwcms.service.common.AbstractService;
import com.nexbis.fwcms.service.user.UserService;

@Service
public class UserServiceImpl extends AbstractService implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.selectUserByUserName(username);

		return user;
	}

}
