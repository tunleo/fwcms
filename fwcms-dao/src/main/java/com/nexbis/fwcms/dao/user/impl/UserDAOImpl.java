package com.nexbis.fwcms.dao.user.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nexbis.fwcms.dao.common.AbstractDao;
import com.nexbis.fwcms.dao.user.UserDAO;
import com.nexbis.fwcms.domain.User;

@Repository
public class UserDAOImpl extends AbstractDao implements UserDAO   {
	
	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public User selectUserByUserName(String username) {
		StringBuilder sqlBuilder = new StringBuilder("");
		sqlBuilder.append(" SELECT USER_NAME as username, PASSWORD as password, APPLICATION_ID as applicationId ");
		sqlBuilder.append(" FROM USER ");
		sqlBuilder.append(" WHERE USER_NAME = ? ");
		User user = null;
		try{
			user = jdbcTemplate.queryForObject(sqlBuilder.toString(),new BeanPropertyRowMapper<User>(User.class),username);
		} catch(EmptyResultDataAccessException e){
			logger.info("Cannot find User in user by name : " + username);
		}
		return user;
	}

}
