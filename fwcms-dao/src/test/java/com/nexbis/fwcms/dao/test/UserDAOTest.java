package com.nexbis.fwcms.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nexbis.fwcms.dao.user.UserDAO;
import com.nexbis.fwcms.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao-context-test.xml"})
@Transactional(readOnly=true)
public class UserDAOTest{
	
	private static final String EXIST_USER_NAME = "user_fwcms";
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void shouldReturnExistUserWhenInputNameOnSelectUserByUserName() throws Exception{
		User user = userDAO.selectUserByUserName(EXIST_USER_NAME);

		Assert.assertNotNull(user);
	}
}
