package com.vodafone.apix.basics.config;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vodafone.apix.basics.ApixConstants;
import com.vodafone.apix.basics.config.ApixGatewayConfiguration;

public class ApixGatewayConfigurationTest {
	private ApixGatewayConfiguration cfg;
	
	@Before
	public void before() {
		cfg = new ApixGatewayConfiguration();
	}
	
	@Test
	public void defaultValue() {
		Assert.assertEquals(ApixConstants.APIX_HOST, cfg.getGatewayHost());
	}

	
	@Test
	public void assignValue() {
		cfg.setGatewayHost("foo.bar");
		Assert.assertEquals("foo.bar", cfg.getGatewayHost());
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void assignNullValue() {
		cfg.setGatewayHost(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void assignBlankValue() {
		cfg.setGatewayHost("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void assignSpacedStringValue() {
		cfg.setGatewayHost(" ");
	}
}
