package com.nexbis.fwcms.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexbis.fwcms.service.utils.FwcmsServiceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-core-context-test.xml"})
public class FwcmsServiceUtilTest {
	
	@Test
	public void shouldReturnUUIDWhenGenerateUUIDOnFwcmsServiceUtil() throws Exception{
		String uuid = FwcmsServiceUtil.generateUUID();

		Assert.assertTrue(uuid.length()==36);
	}
}