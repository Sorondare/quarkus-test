package be.sorondare.quarkus.test.greeting;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface GreetingService {
	@NotNull String getGreeting(@NotNull String name);

	@NotNull Optional<String> getGreeting(@NotNull UUID id, @NotNull String name);
}
