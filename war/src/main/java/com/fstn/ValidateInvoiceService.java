package com.fstn;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateInvoiceService {

	@Inject
	private RegistrerConfig registrer;
	@PostConstruct
	private void init() {
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
    @RequestMapping("/validateInvoice")
	public Context validate(Context context) {
		context.addCall(new StackCall(registrer.getApi().getName() + registrer.getApi().getAction()));
		for (Api api : registrer.getChildApis()) {
			Client client = ClientBuilder.newBuilder().build();
			context = client
					.target(api.getUrl()+api.getName()+"/rest/"+ api.getAction())
					.request()
					.post(Entity.entity(context, MediaType.APPLICATION_JSON),
							Context.class);
		}
		return context;
	}

	@GET
    @RequestMapping("/check")
	public Response check() {
		registrer.getApi();
		return Response.ok().build();
	}

	@GET
    @RequestMapping("/get")
	@Produces("application/json")
	public Invoice get() {
		return new Invoice();
	}

}