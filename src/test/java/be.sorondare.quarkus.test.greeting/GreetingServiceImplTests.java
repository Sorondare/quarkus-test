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
	private final static UUID uuid = UUID.randomUUID();
	private final static UUID unknownUuid = UUID.randomUUID();

	@InjectMock
	private GreetingFormatService formatService;
	private final GreetingServiceImpl service;

	@Inject
	public GreetingServiceImplTests(GreetingServiceImpl service) {
		this.service = service;
	}

	@BeforeEach
	void setup() {
		GreetingFormat format = GreetingFormat.builder().id(uuid).format("Test format %s").build();
		Mockito.when(formatService.findOne(uuid)).thenReturn(Optional.of(format));
		Mockito.when(formatService.findOne(unknownUuid)).thenReturn(Optional.empty());
	}

	@Test
	void testSimpleGreeting() {
		Assertions.assertEquals("Hello test", service.getGreeting("test"));
	}

	@Test
	void testSimpleGreetingException() {
		Assertions.assertThrows(ConstraintViolationException.class, () -> service.getGreeting(null));
	}

	@Test
	void testGreetingFromFormat() {
		Assertions.assertEquals(Optional.of("Test format test"), service.getGreeting(uuid, "test"));
	}

	@Test
	void testUnknownGreetingFromFormat() {
		Assertions.assertEquals(Optional.empty(), service.getGreeting(unknownUuid, "test"));
	}

	@Test
	void testNullIdGreetingFormatException() {
		Assertions.assertThrows(ConstraintViolationException.class, () -> service.getGreeting(null, "test"));
	}

	@Test
	void testNullNameGreetingFormatException() {
		Assertions.assertThrows(ConstraintViolationException.class, () -> service.getGreeting(uuid, null));
	}
}
