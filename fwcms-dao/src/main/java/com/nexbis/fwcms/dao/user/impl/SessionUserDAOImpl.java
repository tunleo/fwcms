package com.nexbis.fwcms.dao.user.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nexbis.fwcms.dao.common.AbstractDao;
import com.nexbis.fwcms.dao.user.SessionUserDAO;
import com.nexbis.fwcms.domain.SessionUser;

@Repository
public class SessionUserDAOImpl extends AbstractDao implements SessionUserDAO {
	
	private static Logger logger = LoggerFactory.getLogger(SessionUserDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert sessionUserJdbcInsert;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.sessionUserJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("SESSION_USER");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
	@Override
	public void insertSessionUser(SessionUser sessionUser){
		SqlParameterSource parameterSource = getSessionUserSqlParameterSoure(sessionUser);
		
		sessionUserJdbcInsert.execute(parameterSource);
	}
	
	@Override
	public int updateSessionUserByUsername(SessionUser sessionUser){
		StringBuilder sqlBuilder = new StringBuilder("");
		
		sqlBuilder.append(" UPDATE SESSION_USER SET ");
		sqlBuilder.append(" TOKEN = :token, AUTH_DATETIME = :authenticationDateTime ");
		sqlBuilder.append(" WHERE USER_NAME = :username ");
		
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(sessionUser);
		
		return namedParameterJdbcTemplate.update(sqlBuilder.toString(), namedParameters);
	}
	
	@Override
	public SessionUser selectSessionUserByUserName(String username) {
		StringBuilder sqlBuilder = new StringBuilder("");
		sqlBuilder.append(" SELECT USER_NAME as username, TOKEN as token, AUTH_DATETIME as authenticationDateTime ");
		sqlBuilder.append(" FROM SESSION_USER ");
		sqlBuilder.append(" WHERE USER_NAME = ? ");
		SessionUser sessionUser = null;
		try{
			sessionUser = jdbcTemplate.queryForObject(sqlBuilder.toString(),new BeanPropertyRowMapper<SessionUser>(SessionUser.class),username);
		} catch(EmptyResultDataAccessException e){
			logger.info("Cannot find User in session user by name : " + username);
		}
		return sessionUser;
	}
	
	private SqlParameterSource getSessionUserSqlParameterSoure(SessionUser sessionUser){
		SqlParameterSource parameterSource = new MapSqlParameterSource()
		.addValue("USER_NAME", sessionUser.getUsername())
		.addValue("TOKEN", sessionUser.getToken())
		.addValue("AUTH_DATETIME", sessionUser.getAuthenticationDateTime());
		
		return parameterSource;
	}

}
