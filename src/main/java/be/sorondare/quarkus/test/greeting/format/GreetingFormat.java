package be.sorondare.quarkus.test.greeting.format;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class GreetingFormat {
	@NotNull
	UUID id;
	@NotBlank
	String format;
}
