package be.sorondare.quarkus.test.greeting;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/greeting/hello")
public class GreetingResource {

	private final GreetingService service;

	@Inject
	public GreetingResource(GreetingService service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> getGreeting(
			@QueryParam("name") String name,
			@QueryParam("format") String formatName
	) {
		return Uni
				.createFrom()
				.item(formatName)
				.map(format -> format == null ? service.getGreeting(name) : service.getGreeting(format, name).orElseThrow(NotFoundException::new));
	}
}
