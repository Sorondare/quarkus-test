package be.sorondare.quarkus.test.greeting;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.UUID;

@Slf4j
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
			@QueryParam("formatName") String formatName,
			@Context SecurityContext securityContext
	) {
		log.info("Call with name {} and format {}", name, formatName);

		return formatName == null ?
				greetingService.getGreeting(name) :
				greetingService
						.getGreeting(formatName, name)
						.orElseThrow(NotFoundException::new);
	}
}
