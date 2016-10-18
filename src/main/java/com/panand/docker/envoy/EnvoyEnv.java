package com.panand.docker.envoy;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.github.dockerjava.api.model.Event;

public class EnvoyEnv {

	public static String getHostName(Event event) {
		
		try {
			return event.getNode().getName();
		} catch(NullPointerException e) {
			try {
				return InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e1) {
				return "default";
			}
		}
	}
}
