package com.nexbis.fwcms.dao.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	protected Timestamp getCurrentTimestamp(){
		Calendar todayCl = Calendar.getInstance(Locale.US);
		return new Timestamp(todayCl.getTimeInMillis());
	}
}
