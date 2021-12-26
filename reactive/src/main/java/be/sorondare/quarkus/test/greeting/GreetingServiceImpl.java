package be.sorondare.quarkus.test.greeting;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
class GreetingServiceImpl implements GreetingService {

	private final GreetingConfig config;

	@Inject
	GreetingServiceImpl(GreetingConfig config) {
		this.config = config;
	}

	@Override
	public String getGreeting(String name) {
		return String.format("Hello %s", name == null ? config.defaultName() : name);
	}
}
