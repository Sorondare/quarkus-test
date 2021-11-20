package be.sorondare.quarkus.test.greeting;

import be.sorondare.quarkus.test.greeting.format.GreetingFormatService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class GreetingServiceImpl implements GreetingService {
	private final GreetingFormatService greetingFormatService;
	private final String defaultName;
	private final String defaultFormat;

	@Inject
	GreetingServiceImpl(
			GreetingFormatService greetingFormatService,
			@ConfigProperty(name = "be.sorondare.greeting.defaultName", defaultValue = "NAME NOT DEFINED") String defaultName,
			@ConfigProperty(name = "be.sorondare.greeting.defaultFormat", defaultValue = "FORMAT NOT DEFINED : %s") String defaultFormat
	) {
		this.greetingFormatService = greetingFormatService;
		this.defaultName = defaultName;
		this.defaultFormat = defaultFormat;
	}

	@Override
	@NotNull
	public String getGreeting(String name) {
		return String.format(defaultFormat, name == null ? defaultName : name);
	}

	@Override
	public @NotNull Optional<String> getGreeting(@NotNull UUID id, String name) {
		return greetingFormatService
				.findOne(id)
				.map(format -> String.format(format.getFormat(), name == null ? defaultName : name));
	}
}
