package be.sorondare.quarkus.test.greeting.format;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class SimpleGreetingFormat {
	@NotBlank
	String format;
}
