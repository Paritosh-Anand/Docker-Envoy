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
 * Created by paritoshanand on 8/2/2016.
 */
public class InspectContainer {
    InspectContainerResponse inspectContainer;

    /**
     * runs docker inspect for a container id and provides required data.
     * @param containerId
     * @throws IOException
     * @throws NullPointerException
     */
    public InspectContainer(String containerId) throws IOException {
        try {
        	/*
        	 *  TODO Check if creating new docker client can 
        	 *  be avoided here.  
        	 */
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

    public Map<ExposedPort, Ports.Binding[]> getPorts(){
        return inspectContainer.getNetworkSettings().getPorts().getBindings();
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
    
    public HashMap<String, String> getHostPort() {
    	HashMap<String, String> portInfo = new HashMap<String, String>();
    	try {
	    	Map<ExposedPort, Ports.Binding[]> portBindings = getPorts();
	    	
	    	for(ExposedPort exposedPort: portBindings.keySet()) {
	    		portInfo.put(String.valueOf(exposedPort.getPort()), portBindings.get(exposedPort)[0].getHostPortSpec());
	    	}
    	} catch (NullPointerException e) {
    		//caught npe while getting ports from network settings
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
