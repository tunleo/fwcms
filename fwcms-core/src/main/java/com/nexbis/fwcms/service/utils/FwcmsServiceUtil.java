package com.nexbis.fwcms.service.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import org.apache.ws.security.WSUsernameTokenPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.nexbis.fwcms.domain.User;

public class FwcmsServiceUtil {

	public static final String FWCMS_SESSION_TIMEOUT_PROPERTY = "FWCMS_SESSION_TIMEOUT";
	
	public static final String generateUUID(){
		// need to pass Ethernet address; can either use real one (shown here)
		EthernetAddress nic = EthernetAddress.fromInterface();
		// or bogus which would be gotten with: EthernetAddress.constructMulticastAddress()
		TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(nic);
		// also: we don't specify synchronizer, getting an intra-JVM syncer; there is
		// also external file-locking-based synchronizer if multiple JVMs run JUG
		UUID uuid = uuidGenerator.generate();
		
		return uuid.toString();
	}
	
	public static final Timestamp getCurrentTimestamp(){
		Calendar todayCl = Calendar.getInstance(Locale.US);
		return new Timestamp(todayCl.getTimeInMillis());
	}
	
	public static final User getCurrentUser(){
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken){
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof WSUsernameTokenPrincipal){
				WSUsernameTokenPrincipal wsUsernameTokenPrincipal = (WSUsernameTokenPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				return new User(wsUsernameTokenPrincipal.getName(),wsUsernameTokenPrincipal.getPassword());
			}
		}

		return null;
	}
	
	public static final String getSessionTimeoutFromProperty(){
		String sessionTimeoutStr = System.getProperty(FWCMS_SESSION_TIMEOUT_PROPERTY);
		
		return sessionTimeoutStr;
	}
	
}
