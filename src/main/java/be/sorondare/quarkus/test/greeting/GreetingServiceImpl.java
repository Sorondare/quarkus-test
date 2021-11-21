package be.sorondare.quarkus.test.greeting;

import be.sorondare.quarkus.test.greeting.format.GreetingFormatService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class GreetingServiceImpl implements GreetingService {
	private final GreetingFormatService greetingFormatService;
	private final GreetingConfig config;

	@Inject
	GreetingServiceImpl(
			GreetingFormatService greetingFormatService,
			@SuppressWarnings("CdiInjectionPointsInspection") GreetingConfig config
	) {
		this.greetingFormatService = greetingFormatService;
		this.config = config;
	}

	@Override
	@NotNull
	public String getGreeting(String name) {
		return String.format(config.defaultFormat(), name == null ? config.defaultName() : name);
	}

	@Override
	public @NotNull Optional<String> getGreeting(@NotNull String formatName, String name) {
		return greetingFormatService
				.findOne(formatName)
				.map(greetingFormat -> String.format(greetingFormat.format(), name == null ? config.defaultName() : name));
	}
}
