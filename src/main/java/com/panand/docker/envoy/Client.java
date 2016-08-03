package com.panand.docker.envoy;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by MMT5898 on 8/2/2016.
 */
public class Client {

    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    /**
     * 
     * @return {@link DockerClient}
     * @throws IOException
     */
    public static DockerClient getDockerClient() throws IOException {
        Properties properties = EnvoyProperties.getEnvoyProperties();

        logger.info("Listening to Docker events on " + properties.getProperty("com.docker.envoy.dockerUri"));
        ConfigBuilder configBuilder = new ConfigBuilder(properties);

        DockerClient dockerClient = DockerClientBuilder.getInstance(configBuilder.generateConfigs())
                .withDockerCmdExecFactory(configBuilder.generateCmdExecFactory())
                .build();
        //DockerClient dockerClient = DockerClientBuilder.getInstance(properties.getProperty("com.docker.envoy.dockerUri")).build();

        return dockerClient;
    }

}
