package com.fstn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Startup
@Singleton
public class RegistrerConfig {
	List<Api> childApis;
	Api api;

	private String wsParentName;
	private String wsName;
	private String wsAction;
	private String wsRegisterAction;
	private String wsURL = "http://127.0.0.1:8080/";

	private static final Logger log = Logger.getLogger(RegistrerConfig.class
			.getName());

	@PostConstruct
	public void init() {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("ws.properties"));
		} catch (IOException e) {
			log.log(Level.SEVERE, "can't find properties ", e);
		}

		childApis = new ArrayList<Api>();
		api = new Api();
		api.setName(props.getProperty("wsName").trim());
		api.setAction(props.getProperty("wsAction").trim());
		api.setUrl(props.getProperty("wsURL").trim());
		api.setParentName(props.getProperty("wsParentName").trim());
		log.log(Level.SEVERE, "init " + api.toString());
		
		

		if (!api.getParentName().equals("")) {
			api.setParentRegistrerAction(props.getProperty("wsRegisterAction"));

			childApis = new ArrayList<Api>();
 
			RegisterInit registerInit = new RegisterInit(this);
			registerInit.start();
			}
		}
	
	@PreDestroy
	public void destroy() {
		Client client = ClientBuilder.newBuilder().build();
		Response result = client
				.target(getApi().getUrl() + "/"
						+ getApi().getParentName() + "/rest/"
						+ "unRegisterChild")
				.request()
				.post(Entity.entity(getApi(),
						MediaType.APPLICATION_JSON), Response.class);
		client.close();
	}
	

	public void registerChildApi(Api childApi) {
		childApis.add(childApi);
	}
	
	public void unRegisterChildApi(Api childApi) {
		childApis.remove(childApi);		
	}

	/**
	 * @return the childApis
	 */
	public List<Api> getChildApis() {
		return childApis;
	}

	/**
	 * @param childApis
	 *            the childApis to set
	 */
	public void setChildApis(List<Api> childApis) {
		this.childApis = childApis;
	}

	/**
	 * @return the api
	 */
	public Api getApi() {
		return api;
	}

	/**
	 * @param api
	 *            the api to set
	 */
	public void setApi(Api api) {
		this.api = api;
	}

	/**
	 * @return the wsParentName
	 */
	public String getWsParentName() {
		return wsParentName;
	}

	/**
	 * @param wsParentName
	 *            the wsParentName to set
	 */
	public void setWsParentName(String wsParentName) {
		this.wsParentName = wsParentName;
	}

	/**
	 * @return the wsName
	 */
	public String getWsName() {
		return wsName;
	}

	/**
	 * @param wsName
	 *            the wsName to set
	 */
	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	/**
	 * @return the wsAction
	 */
	public String getWsAction() {
		return wsAction;
	}

	/**
	 * @param wsAction
	 *            the wsAction to set
	 */
	public void setWsAction(String wsAction) {
		this.wsAction = wsAction;
	}

	/**
	 * @return the wsRegisterAction
	 */
	public String getWsRegisterAction() {
		return wsRegisterAction;
	}

	/**
	 * @param wsRegisterAction
	 *            the wsRegisterAction to set
	 */
	public void setWsRegisterAction(String wsRegisterAction) {
		this.wsRegisterAction = wsRegisterAction;
	}

	/**
	 * @return the wsURL
	 */
	public String getWsURL() {
		return wsURL;
	}

	/**
	 * @param wsURL
	 *            the wsURL to set
	 */
	public void setWsURL(String wsURL) {
		this.wsURL = wsURL;
	}


}
