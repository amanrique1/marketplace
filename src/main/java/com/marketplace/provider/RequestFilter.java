package com.marketplace.provider;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestFilter implements ContainerRequestFilter {

	/**
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext request) {
		request.setProperty("invocationTime", Instant.now());
		String requestId = UUID.randomUUID().toString();
		request.setProperty("requestID", requestId);
		StringBuilder textBuilder = new StringBuilder("");
		if (request.getEntityStream()!=null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				request.getEntityStream().transferTo(baos);
				InputStream firstClone = new ByteArrayInputStream(baos.toByteArray()); 
				InputStream secondClone = new ByteArrayInputStream(baos.toByteArray());
				try (Reader reader = new BufferedReader(new InputStreamReader
						(firstClone, Charset.forName(StandardCharsets.UTF_8.name())))) {
					int c = 0;
					while ((c = reader.read()) != -1) {
						textBuilder.append((char) c);
					}
				}
				request.setEntityStream(secondClone);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setProperty("jsonIn", textBuilder.toString());
	}

}