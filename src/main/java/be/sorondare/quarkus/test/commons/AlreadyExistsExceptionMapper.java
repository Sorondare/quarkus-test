package be.sorondare.quarkus.test.commons;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyExistsExceptionMapper implements ExceptionMapper<AlreadyExistsException> {
	@Override
	public Response toResponse(AlreadyExistsException exception) {
		return Response
				.status(Response.Status.CONFLICT)
				.entity(new ErrorMessage("already exists"))
				.build();
	}
}
