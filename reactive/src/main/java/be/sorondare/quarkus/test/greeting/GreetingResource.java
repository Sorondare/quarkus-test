package be.sorondare.quarkus.test.greeting;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

	private final GreetingService service;

	@Inject
	public GreetingResource(GreetingService service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting(@QueryParam("name") String name) {
		return service.getGreeting(name);
	}
}
