package com.senac.exceptions;

import com.senac.exceptions.model.ConstraintViolationMessage;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collection;

@Provider
public class ConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(final ConstraintViolationException e) {
        final Collection<PropriedadeConstraintMessage> mensagens = e.getConstraintViolations().stream()
                .map(c -> new PropriedadeConstraintMessage(c.getPropertyPath().toString(), c.getMessageTemplate()))
                .toList();
        return Response.status(Response.Status.BAD_REQUEST).entity(new ConstraintViolationMessage(Response.Status.BAD_REQUEST, mensagens)).build();
    }
}
