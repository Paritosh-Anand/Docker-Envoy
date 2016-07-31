package com.panand.docker.envoy;

import java.util.Properties;

import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.DockerCmdExecFactoryImpl;

public class ConfigBuilder {
	
	private static Properties properties;
	public ConfigBuilder(Properties properties) {
		ConfigBuilder.properties=properties;
	}

	public DockerClientConfig generateConfigs() {
		DockerClientConfig clientConfig = DockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost(properties.getProperty("com.docker.envoy.dockerUri"))
    			.withDockerTlsVerify(true)
    			.withDockerCertPath(properties.getProperty("com.docker.envoy.certpath"))
    			.build();
		
		return clientConfig;
	}
	
	public DockerCmdExecFactory generateCmdExecFactory() {
		@SuppressWarnings("resource")
		DockerCmdExecFactory dockerCmdExecFactory = new DockerCmdExecFactoryImpl()
    			.withReadTimeout(-1)
    			.withConnectTimeout(1000)
    			.withMaxTotalConnections(100)
    			.withMaxPerRouteConnections(10);
		
		return dockerCmdExecFactory;
	}
}
