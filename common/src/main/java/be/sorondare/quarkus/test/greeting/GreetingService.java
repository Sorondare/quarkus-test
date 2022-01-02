package be.sorondare.quarkus.test.greeting;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface GreetingService {
	@NotNull String getGreeting(String name);

	@NotNull Optional<String> getGreeting(@NotNull String formatName, String name);
}
