package com.vodafone.apix.basics;

import org.apache.commons.lang3.StringUtils;

public class ApixGatewayConfiguration {
	private String gatewayHost = ApixConstants.APIX_HOST;

	/**
	 * @return the gatewayHost
	 */
	public String getGatewayHost() {
		return gatewayHost;
	}

	/**
	 * @param gatewayHost the gatewayHost to set
	 */
	public void setGatewayHost(String gatewayHost) {
		if(StringUtils.isBlank(gatewayHost))
			throw new IllegalArgumentException();
		
		this.gatewayHost = gatewayHost;
	}
}
