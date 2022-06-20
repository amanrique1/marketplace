package com.marketplace.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import com.marketplace.util.JsonParser;

@Provider
public class ResponseFilter implements ContainerResponseFilter {

	/**
	 * logger
	 */
	private static final Logger logger = Logger.getLogger(ResponseFilter.class);

	/**
	 * @see javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.container.ContainerRequestContext, javax.ws.rs.container.ContainerResponseContext)
	 */
	@Override
	public void filter(ContainerRequestContext reqContext, ContainerResponseContext respContext) {
		String mensaje ="";
		String endpoint = reqContext.getUriInfo().getPath();
		Boolean containsApi = endpoint.contains("/api/");
		if(respContext.getStatus() != 404 && containsApi) {
			String accessToken = (String) Optional.ofNullable(reqContext.getProperty("accessToken")).orElse("N/A");
			String processingTime = ChronoUnit.MILLIS.between((Instant) reqContext.getProperty("invocationTime"),
					Instant.now()) + " ms.";
			String requestID = reqContext.getProperty("requestID").toString();
			String httpMethod = reqContext.getMethod();
			String jsonIn = reqContext.getProperty("jsonIn").toString();
			mensaje = "RequestID:[" + requestID + "] Endpoint:"+ endpoint+ " Tiempo de procesamiento:" + processingTime + " Method:" + httpMethod
					+ " AccessToken: "+ accessToken + " status:" + respContext.getStatus() + " \n JSON In: " + jsonIn;
			if (respContext.getEntity() != null) {
				String jsonOut = " \n JSON Out: "+ JsonParser.toJson(respContext.getEntity());
				mensaje += jsonOut;
			}
			logger.info(mensaje);
		}else if(containsApi) {
			String httpMethod = reqContext.getMethod();
			mensaje = "NOT FOUND 404 Endpoint:"+ endpoint+ " Method:" + httpMethod;
			logger.info(mensaje);
		} else {
			String httpMethod = reqContext.getMethod();
			mensaje = "Request Endpoint Front:"+ endpoint + " Method:" + httpMethod
					+ " status:" + respContext.getStatus();
			logger.info(mensaje);
		}
	}
}