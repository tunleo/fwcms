package com.nexbis.fwcms.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-core-context-test.xml"})
@Transactional(readOnly=true)
public class UserServiceTest{
	private static final String EXIST_USER_NAME = "user_fwcms";
	
	@Autowired
	private UserService userService;
	
	@Test
	public void shouldReturnExistUserWhenInputNameOnLoadUserByUsername() throws Exception{
		UserDetails userDetails = userService.loadUserByUsername(EXIST_USER_NAME);

		Assert.assertNotNull(userDetails);
	}
}
