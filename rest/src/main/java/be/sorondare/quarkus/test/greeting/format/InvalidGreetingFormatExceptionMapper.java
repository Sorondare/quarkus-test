package be.sorondare.quarkus.test.greeting.format;

import be.sorondare.quarkus.test.commons.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidGreetingFormatExceptionMapper implements ExceptionMapper<InvalidGreetingFormatException> {

	@Override
	public Response toResponse(InvalidGreetingFormatException exception) {
		return Response
				.serverError()
				.entity(new ErrorMessage("Invalid format"))
				.build();
	}
}
