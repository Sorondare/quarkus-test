package be.sorondare.quarkus.test.greeting;

import be.sorondare.quarkus.test.greeting.format.GreetingFormat;
import be.sorondare.quarkus.test.greeting.format.GreetingFormatService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.UUID;

@QuarkusTest
public class GreetingServiceImplTests {
	private static final String formatName = "test";
	private static final String unknownFormatName = "unknown";

	@InjectMock
	private GreetingFormatService formatService;
	private final GreetingServiceImpl service;

	@Inject
	public GreetingServiceImplTests(GreetingServiceImpl service) {
		this.service = service;
	}

	@BeforeEach
	void setup() {
		GreetingFormat format = new GreetingFormat(UUID.randomUUID(), formatName, "Test format %s");
		Mockito.when(formatService.findOne(formatName)).thenReturn(Optional.of(format));
		Mockito.when(formatService.findOne(unknownFormatName)).thenReturn(Optional.empty());
	}

	@Test
	void testSimpleGreeting() {
		Assertions.assertEquals("Hello test", service.getGreeting(formatName));
	}

	@Test
	void testSimpleGreetingException() {
		Assertions.assertEquals("Hello Me", service.getGreeting(null));
	}

	@Test
	void testGreetingFromFormat() {
		Assertions.assertEquals(Optional.of("Test format test"), service.getGreeting("test", "test"));
	}

	@Test
	void testUnknownGreetingFromFormat() {
		Assertions.assertEquals(Optional.empty(), service.getGreeting(unknownFormatName, "test"));
	}

	@Test
	void testNullIdGreetingFormatException() {
		Assertions.assertThrows(ConstraintViolationException.class, () -> service.getGreeting(null, "test"));
	}

	@Test
	void testNullNameGreetingFormatException() {
		Assertions.assertEquals(Optional.of("Test format Me"), service.getGreeting(formatName, null));
	}
}
