package be.sorondare.quarkus.test.greeting;

import be.sorondare.quarkus.test.greeting.format.GreetingFormatService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
class GreetingServiceImpl implements GreetingService {

	private final GreetingConfig config;
	private final GreetingFormatService formatService;

	@Inject
	GreetingServiceImpl(GreetingFormatService formatService, GreetingConfig config) {
		this.config = config;
		this.formatService = formatService;
	}

	@Override
	public String getGreeting(String name) {
		return String.format("Hello %s", name == null ? config.defaultName() : name);
	}

	@Override
	public Optional<String> getGreeting(String formatName, String name) {
		return formatService
				.findOne(formatName)
				.map(format -> String.format(format.format(), name));
	}
}
