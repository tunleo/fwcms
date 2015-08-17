package com.nexbis.fwcms.dao.request.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nexbis.fwcms.dao.common.AbstractDao;
import com.nexbis.fwcms.dao.request.RequestDAO;
import com.nexbis.fwcms.domain.ws.SubmitRequestResult;
import com.nexbis.fwcms.domain.ws.xml.SubmitRequestData;

@Repository
public class RequestDAOImpl extends AbstractDao implements RequestDAO {
	private static Logger logger = LoggerFactory.getLogger(RequestDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert submitRequestJdbcInsert;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.submitRequestJdbcInsert =
                new SimpleJdbcInsert(dataSource)
                        .withTableName("SUBMIT_REQUEST");
    }
	
    @Override
    public SubmitRequestData selectSubmitRequestByRequestId(String requestId) {
		StringBuilder sqlBuilder = new StringBuilder("");
		sqlBuilder.append(" SELECT  ");
		sqlBuilder.append(" REQUEST_ID as requestId,DOCTYPE as docType,DOC_NO as docNo, ");
		sqlBuilder.append(" DOC_EXPIRY as docExpiry,ISSUE_AUTHORITY as issueAuthority,SURNAME as surName, ");
		sqlBuilder.append(" GIVENNAME as givenName,DATE_OF_BIRTH as dateOfBirth,NATIONALITY as nationality, ");
		sqlBuilder.append(" GENDER as gender,PHOTO as photo,RIGHT_THUMB as rightThumb, ");
		sqlBuilder.append(" RIGHT_INDEX as rightIndex,RIGHT_MIDDLE as rightMiddle,RIGHT_RING as rightRing, ");
		sqlBuilder.append(" RIGHT_LITTLE as rightLittle,LEFT_THUMB as leftThumb,LEFT_INDEX as leftIndex, ");
		sqlBuilder.append(" LEFT_MIDDLE as leftMiddle,LEFT_RING as leftRing,LEFT_LITTLE as leftLittle, ");
		sqlBuilder.append(" SUBMIT_REMARKS as submitRemarks,STATUS_CODE as statusCode, ");
		sqlBuilder.append(" GET_RESULT_REMARKS as getResultRemarks,STATUS_RESULT as statusResult,DATETIME_PROCESS as dateTimeProcess, ");
		sqlBuilder.append(" DATETIME_SUBMIT as dateTimeSubmit,DATETIME_REQUEST as dateTimeRequest, DATETIME_GETRESULT as dateTimeGetResult,  ");
		sqlBuilder.append(" ENTRY_DATE as entryDate,ENTRY_EXPIRY as entryExpiry, OPT_DATA1 as optData1,  ");
		sqlBuilder.append(" OPT_DATA2 as optData2,remarks as remarks  ");
		sqlBuilder.append(" FROM SUBMIT_REQUEST  ");
		sqlBuilder.append(" WHERE REQUEST_ID = ? ");
		SubmitRequestData submitRequest = null;
		try{
			submitRequest = jdbcTemplate.queryForObject(sqlBuilder.toString(),new BeanPropertyRowMapper<SubmitRequestData>(SubmitRequestData.class),requestId);
		} catch(EmptyResultDataAccessException e){
			logger.info("Cannot find Submit request ID : " + requestId);
		}
		return submitRequest;
	}
    
	@Override
	public void insertSubmitRequest(SubmitRequestData submitRequest) {
		SqlParameterSource parameterSource = getSubmitRequestSqlParameterSoure(submitRequest);
		
        int insertRecord = submitRequestJdbcInsert.execute(parameterSource);
        
        logger.debug("insert Record = " + insertRecord);
	}
	
	@Override
	public List<SubmitRequestResult> insertSubmitRequest(List<SubmitRequestData> submitRequests) {
		List<SubmitRequestResult> submitRequestResults = new ArrayList<SubmitRequestResult>();
		if(submitRequests != null && submitRequests.size()>0){
			int index = 1;
			List<SqlParameterSource> listParameterSource = new ArrayList<SqlParameterSource>();
			for(SubmitRequestData submitRequest : submitRequests){
				SqlParameterSource parameterSource = getSubmitRequestSqlParameterSoure(submitRequest);
				
				listParameterSource.add(parameterSource);
				
				submitRequestResults.add(new SubmitRequestResult(index++, submitRequest.getRequestId()));
			}
			
			SqlParameterSource[] arrayParameterSource = listParameterSource.toArray(new SqlParameterSource[listParameterSource.size()]);
			
			int[] insertRecords = submitRequestJdbcInsert.executeBatch(arrayParameterSource);
			
			logger.debug("insert Record = " + insertRecords);
		}
		
		return submitRequestResults;
    }
	
	@Override
	public int updateGetResultDateTimeByRequestId(String requestId, Timestamp getResultDateime) {
		StringBuilder sqlBuilder = new StringBuilder(" ");
		sqlBuilder.append(" UPDATE SUBMIT_REQUEST ");
		sqlBuilder.append(" SET DATETIME_GETRESULT = ? ");
		sqlBuilder.append(" WHERE REQUEST_ID = ? ");
		
		return jdbcTemplate.update(sqlBuilder.toString(), getResultDateime, requestId);
	}
	
	private SqlParameterSource getSubmitRequestSqlParameterSoure(SubmitRequestData submitRequest){
		SqlParameterSource parameterSource = new MapSqlParameterSource()
		.addValue("REQUEST_ID", submitRequest.getRequestId())
		.addValue("DOCTYPE", submitRequest.getDocType())
		.addValue("DOC_NO", submitRequest.getDocNo())
		.addValue("DOC_EXPIRY", submitRequest.getDocExpiry())
		.addValue("ISSUE_AUTHORITY", submitRequest.getIssueAuthority())
		.addValue("SURNAME", submitRequest.getSurName())
		.addValue("GIVENNAME", submitRequest.getGivenName())
		.addValue("DATE_OF_BIRTH", submitRequest.getDateOfBirth())
		.addValue("NATIONALITY", submitRequest.getNationality())
		.addValue("GENDER", submitRequest.getGender())
		.addValue("ENTRY_DATE", submitRequest.getEntryDate())
		.addValue("ENTRY_EXPIRY", submitRequest.getEntryExpiry())
		.addValue("OPT_DATA1", submitRequest.getOptData1())
		.addValue("OPT_DATA2", submitRequest.getOptData2())
		.addValue("REMARKS", submitRequest.getRemarks())
		.addValue("PHOTO", submitRequest.getPhoto())
		.addValue("RIGHT_THUMB", submitRequest.getRightThumb())
		.addValue("RIGHT_INDEX", submitRequest.getRightIndex())
		.addValue("RIGHT_MIDDLE", submitRequest.getRightMiddle())
		.addValue("RIGHT_RING", submitRequest.getRightRing())
		.addValue("RIGHT_LITTLE", submitRequest.getRightLittle())
		.addValue("LEFT_THUMB", submitRequest.getLeftThumb())
		.addValue("LEFT_INDEX", submitRequest.getLeftIndex())
		.addValue("LEFT_MIDDLE", submitRequest.getLeftMiddle())
		.addValue("LEFT_RING", submitRequest.getLeftRing())
		.addValue("LEFT_LITTLE", submitRequest.getLeftLittle())
		.addValue("REQUEST_ID", submitRequest.getRequestId())
		.addValue("SUBMIT_REMARKS", submitRequest.getSubmitRemarks())
		.addValue("STATUS_CODE", submitRequest.getStatusCode())
		.addValue("GET_RESULT_REMARKS", submitRequest.getGetResultRemarks())
		.addValue("STATUS_RESULT", submitRequest.getStatusResult())
		.addValue("DATETIME_PROCESS", submitRequest.getDateTimeProcess())
		.addValue("DATETIME_SUBMIT", submitRequest.getDateTimeSubmit())
		.addValue("DATETIME_REQUEST", submitRequest.getDateTimeRequest())
		.addValue("DATETIME_GETRESULT", submitRequest.getDateTimeGetResult());
		
		return parameterSource;
	}
}
