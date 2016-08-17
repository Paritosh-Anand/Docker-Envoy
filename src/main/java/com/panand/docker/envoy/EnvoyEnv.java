package com.panand.docker.envoy;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.github.dockerjava.api.model.Event;

public class EnvoyEnv {

	public static String getHostName(Event event) {
		
		try {
			if(event.getNode().getName() != null) {
				return event.getNode().getName();
			} else {
				try {
					return InetAddress.getLocalHost().getHostName();
				} catch (UnknownHostException e) {
					return "default";
				}
			}
		} catch(NullPointerException e) {
			return "default";
		}
	}
}
