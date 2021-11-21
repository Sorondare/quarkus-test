package be.sorondare.quarkus.test.greeting.format;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record GreetingFormat(@NotNull UUID id, @NotNull String name, @NotBlank String format) {
}
