package be.sorondare.quarkus.test.greeting;

import be.sorondare.quarkus.test.greeting.format.GreetingFormatService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class GreetingServiceImpl implements GreetingService {
	public static final String DEFAULT_FORMAT = "Hello %s";

	private final GreetingFormatService greetingFormatService;

	@Inject
	GreetingServiceImpl(GreetingFormatService greetingFormatService) {
		this.greetingFormatService = greetingFormatService;
	}

	@Override
	@NotNull
	public String getGreeting(@NotNull String name) {
		return String.format(DEFAULT_FORMAT, name);
	}

	@Override
	public @NotNull Optional<String> getGreeting(@NotNull UUID id, @NotNull String name) {
		return greetingFormatService
				.findOne(id)
				.map(format -> String.format(format.getFormat(), name));
	}
}
