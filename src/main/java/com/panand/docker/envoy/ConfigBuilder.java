package com.panand.docker.envoy;

import java.util.Properties;

import com.github.dockerjava.core.DefaultDockerClientConfig;

public class ConfigBuilder {
	
	private static Properties properties;
	
	/**
	 * @param properties
	 */
	public ConfigBuilder(Properties properties) {
		ConfigBuilder.properties=properties;
	}

	/**
	 * 
	 * @return {@link DefaultDockerClientConfig}
	 */
	public DefaultDockerClientConfig generateConfigs() {

		DefaultDockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost(properties.getProperty("com.docker.envoy.dockerUri"))
    			.withDockerTlsVerify(Boolean.getBoolean(properties.getProperty("com.docker.envoy.tlsverify")))
    			.withDockerCertPath(properties.getProperty("com.docker.envoy.certpath"))
    			.build();

		return clientConfig;
	}

}
