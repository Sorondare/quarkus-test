package be.sorondare.quarkus.test.greeting;

import io.quarkus.logging.Log;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.UUID;

@Path("/greeting/hello")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {
	private final GreetingService greetingService;

	@Inject
	GreetingResource(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GET
	@RolesAllowed("user")
	public String greeting(
			@QueryParam("name") String name,
			@QueryParam("formatId") UUID formatId,
			@Context SecurityContext securityContext
	) {
		Log.infof("Call with name %s and formatId %s", name, formatId);

		return formatId == null ?
				greetingService.getGreeting(name) :
				greetingService
						.getGreeting(formatId, name)
						.orElseThrow(NotFoundException::new);
	}
}
