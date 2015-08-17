package com.nexbis.fwcms.domain.ws.xml.adapter;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlTimestampAdapter extends XmlAdapter<java.util.Date, java.sql.Timestamp> {

	@Override
	public Timestamp unmarshal(Date utilDate) throws Exception {
		if(null == utilDate) {
            return null;
        }
        return new Timestamp(utilDate.getTime());
	}

	@Override
	public Date marshal(Timestamp timestamp) throws Exception {
		if(null == timestamp) {
            return null;
        }
        return new Date(timestamp.getTime());
	}

}
