package com.senac.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(final Exception e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionMessage(Response.Status.BAD_REQUEST, e.getMessage())).build();
    }
}
