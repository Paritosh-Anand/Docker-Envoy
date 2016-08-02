package com.panand.docker.envoy.container;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.panand.docker.envoy.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MMT5898 on 8/2/2016.
 */
public class InspectContainer {
    InspectContainerResponse inspectContainer;

    public InspectContainer(String containerId) throws IOException {
        try {
            DockerClient dockerClient = Client.getDockerClient();
            inspectContainer = dockerClient.inspectContainerCmd(containerId).exec();
        } catch (NotFoundException e) {
            throw new NullPointerException("container not found");
        }
    }

    public String getContainerStatus() {
        return inspectContainer.getState().getStatus();
    }

    public String getContainerImageId() {
        return inspectContainer.getImageId();
    }

    public Ports getPorts(){
        return inspectContainer.getNetworkSettings().getPorts();
    }

    public Map<ExposedPort, Ports.Binding[]> getHostPortBindings(){
        return inspectContainer.getHostConfig().getPortBindings().getBindings();
    }

    public HashMap<String, String> getHostExposedPort() {
        HashMap<String, String> portInfo = new HashMap<String, String>();
        Map<ExposedPort, Ports.Binding[]> portBindings = getHostPortBindings();

        for(ExposedPort exposedPort: portBindings.keySet()) {
            portInfo.put(String.valueOf(exposedPort.getPort()), portBindings.get(exposedPort)[0].getHostPortSpec());
        }
        return portInfo;
    }

    public Map<String, String> getLabels() {
        return inspectContainer.getConfig().getLabels();
    }

    public Boolean getOOMkilled() {
        return inspectContainer.getState().getOOMKilled();
    }

}