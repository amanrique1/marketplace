package com.marketplace.provider;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.marketplace.exceptions.WSException;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class WSExceptionMapper implements ExceptionMapper<WSException> {

	/**
	 * Catches the WSException, creates a LoggeableException from its data
	 * and returns a Response object with the LoggeableException embedded in the entity field.
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 * @param e the WSException to be thrown based on its status
	 */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(WSException e) {
		if(e.getStatus().equals(Response.Status.INTERNAL_SERVER_ERROR)) {
			LoggeableException lo = new LoggeableException();
			lo.setMessage(e.getMessage());
			Writer writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			lo.setCause(writer.toString());
			return Response.status(e.getStatus()).entity(lo).type(MediaType.APPLICATION_JSON).build();
		}else {
			LoggeableException lo  = new LoggeableException(e.getMessage());
			return Response.status(e.getStatus()).entity(lo).type(MediaType.APPLICATION_JSON).build();
		}	
		
	}
}