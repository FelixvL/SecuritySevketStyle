package com.security.presentatie.config;

import javax.ws.rs.ApplicationPath;

import com.security.presentatie.api.TreinEndpoint;
import com.security.presentatie.api.UserEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig(){
		register(TreinEndpoint.class);
		register(UserEndpoint.class);
	}
}
