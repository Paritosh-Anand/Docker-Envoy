package com.panand.docker.envoy;

import java.util.Properties;

import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.jaxrs.DockerCmdExecFactoryImpl;

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
    			.withDockerTlsVerify(true)
    			.withDockerCertPath(properties.getProperty("com.docker.envoy.certpath"))
    			.build();

		return clientConfig;
	}

	/**
	 * 
	 * @return {@link DockerCmdExecFactory}
	 */
	public DockerCmdExecFactory generateCmdExecFactory() {
		DockerCmdExecFactory dockerCmdExecFactory = new DockerCmdExecFactoryImpl()
    			.withReadTimeout(-1)
    			.withConnectTimeout(1000)
    			.withMaxTotalConnections(100)
    			.withMaxPerRouteConnections(10);
		
		return dockerCmdExecFactory;
	}
}
