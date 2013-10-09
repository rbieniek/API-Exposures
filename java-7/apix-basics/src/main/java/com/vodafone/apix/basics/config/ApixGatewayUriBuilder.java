/**
 * 
 */
package com.vodafone.apix.basics.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.apix.basics.ApixConstants;
import com.vodafone.apix.basics.ApixRuntimeException;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class ApixGatewayUriBuilder {
	private static final Logger log = LoggerFactory.getLogger(ApixGatewayUriBuilder.class);
	
	/*
	 * Instances are created though factory method.
	 */
	private ApixGatewayUriBuilder() {}

	private ApixGatewayConfiguration cfg;
	
	private void setConfiguration(ApixGatewayConfiguration cfg) {
		this.cfg = cfg;
	}
	
	public static ApixGatewayUriBuilder fromConfiguration(ApixGatewayConfiguration cfg) {
		ApixGatewayUriBuilder builder = new ApixGatewayUriBuilder();

		builder.setConfiguration(cfg);
		return builder;
	}
	
	public URI buildOAuth2Endpoint() {
		try {
			return new URI(ApixConstants.SECURE_SCHEME, 
					cfg.getGatewayHost(), 
					ApixConstants.PATH_OAUTH2_ENDPOINT, null);
		} catch (URISyntaxException e) {
			log.error("failed to build OAuth2 endpoint URI", e);
			
			throw new ApixRuntimeException(e);
		}
	}
	
}
