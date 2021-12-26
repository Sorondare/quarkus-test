package be.sorondare.quarkus.test.greeting.format;

import be.sorondare.quarkus.test.commons.AlreadyExistsException;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.UUID;

@Tag(name = "GreetingFormat", description = "Operations to manage greeting formats")
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

	@GET
	@Path("/search")
	public GreetingFormat getFormat(@QueryParam("formatName") String formatName) {
		return formatService
				.findOne(formatName)
				.orElseThrow(NotFoundException::new);
	}

	@APIResponse(
		responseCode = "201", description = "Created"
	)
	@POST
	public Response createFormat(@Valid SimpleGreetingFormat request, @Context UriInfo uriInfo) throws InvalidGreetingFormatException, AlreadyExistsException {
		GreetingFormat format = formatService.create(mapper.toDto(request));
		return Response
				.created(UriBuilder.fromPath(uriInfo.getPath()).path(format.id().toString()).build())
				.entity(format)
				.build();
	}

	@PUT
	@Path("/{id}")
	public void updateFormat(@PathParam("id") UUID id, @Valid SimpleGreetingFormat format) throws InvalidGreetingFormatException {
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
