package com.panand.docker.envoy.container;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by MMT5898 on 8/2/2016.
 */
public class EnvoyProperties {

    public static Properties getEnvoyProperties() throws IOException {

        Properties properties = new Properties();
        FileReader rd = new FileReader("E:\\GitRepos\\Docker-Envoy\\docker.properties");
        properties.load(rd);

        return properties;
    }

}
