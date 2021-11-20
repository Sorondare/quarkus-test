package be.sorondare.quarkus.test.greeting.format;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/greeting/format")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("admin")
public class GreetingFormatResource {
	private final GreetingFormatService formatService;
	private final GreetingFormatMapper mapper;

	@Inject
	GreetingFormatResource(GreetingFormatService formatService, GreetingFormatMapper mapper) {
		this.formatService = formatService;
		this.mapper = mapper;
	}

	@GET
	public List<GreetingFormat> getFormats() {
		return formatService
				.findAll();
	}

	@GET
	@Path("/{id}")
	public GreetingFormat getFormat(@PathParam("id") UUID id) {
		return formatService
				.findOne(id)
				.orElseThrow(NotFoundException::new);
	}

	@POST
	public GreetingFormat createFormat(@Valid SimpleGreetingFormat request) throws EmptyFormatException {
		return formatService.create(request.getFormat());
	}

	@PUT
	@Path("/{id}")
	public void updateFormat(@PathParam("id") UUID id, @Valid SimpleGreetingFormat format) throws EmptyFormatException {
		formatService.update(
				mapper.toDto(id, format)
		);
	}

	@DELETE
	@Path("/{id}")
	public void updateFormat(@PathParam("id") UUID id) {
		formatService.delete(id);
	}
}
