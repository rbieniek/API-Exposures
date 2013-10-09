/**
 * 
 */
package com.vodafone.apix.basics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class ApixGatewayUriBuilderTest {

	private ApixGatewayUriBuilder builder;

	@Before
	public void before() {
		ApixGatewayConfiguration cfg = Mockito.mock(ApixGatewayConfiguration.class);
		
		Mockito.when(cfg.getGatewayHost()).thenReturn("api.developer.vodafone.com");
		
		this.builder = ApixGatewayUriBuilder.fromConfiguration(cfg);
	}
	
	@Test
	public void checkOAuth2Endpoint() {
		Assert.assertEquals("https://api.developer.vodafone.com/2/oauth/access-token", builder.buildOAuth2Endpoint().toString());
	}
}
