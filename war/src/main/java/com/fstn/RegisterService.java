package com.fstn;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class RegisterService {

	private static final Logger log = Logger.getLogger(RegisterService.class
			.getName());

	@Inject
	private RegistrerConfig registrer;

	@PostConstruct
	private void init(){	
	}
			
	@POST
	@Path("/registerChild")
	@Consumes("application/json")
	public Response registerChild(Api childApi) {
		log.log(Level.SEVERE,"register "+childApi.toString());
		registrer.registerChildApi(childApi);	
		return Response.ok().build();
	}
	
	@POST
	@Path("/unRegisterChild")
	@Consumes("application/json")
	public Response unRegisterChild(Api childApi) {
		log.log(Level.SEVERE,"unRegister "+childApi.toString());
		registrer.unRegisterChildApi(childApi);	
		return Response.ok().build();
	}
}