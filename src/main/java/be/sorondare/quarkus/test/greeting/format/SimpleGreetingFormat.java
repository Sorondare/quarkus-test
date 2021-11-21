package be.sorondare.quarkus.test.greeting.format;

import javax.validation.constraints.NotBlank;

public record SimpleGreetingFormat(@NotBlank String format) {
}
