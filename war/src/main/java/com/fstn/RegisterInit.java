package com.fstn;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RegisterInit extends Thread {

	private RegistrerConfig register;
	private boolean registered = false;

	private static final Logger log = Logger.getLogger(RegistrerConfig.class
			.getName());

	public RegisterInit(RegistrerConfig register) {
		super();
		this.register = register;
	}

	public void run() {
		int currentTry = 0;
		while (!registered && currentTry < 100) {
			currentTry++;
			Client client = ClientBuilder.newBuilder().build();
			Response result = client
					.target(register.getApi().getUrl() + "/"
							+ register.getApi().getParentName() + "/rest/"
							+ register.getApi().getParentRegistrerAction())
					.request()
					.post(Entity.entity(register.getApi(),
							MediaType.APPLICATION_JSON), Response.class);
			client.close();
			if (result.getStatus() == 404) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "error " + register.getApi());
				}
			} else {
				registered = true;
			}
		}

		if (!registered) {
			throw new RuntimeException("Unable to find parent API "
					+ register.getApi());
		}
	}
}
