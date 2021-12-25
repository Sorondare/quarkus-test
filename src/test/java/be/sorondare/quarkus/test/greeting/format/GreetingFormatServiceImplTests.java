package be.sorondare.quarkus.test.greeting.format;

import be.sorondare.quarkus.test.commons.AlreadyExistsException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@QuarkusTest
public class GreetingFormatServiceImplTests {

	@InjectMock
	private GreetingFormatRepository formatRepository;

	private final GreetingFormatServiceImpl service;

	@Inject
	GreetingFormatServiceImplTests(GreetingFormatServiceImpl service) {
		this.service = service;
	}

	@Test
	void create() throws InvalidGreetingFormatException, AlreadyExistsException {
		final var format = new GreetingFormat(null,"test", "Test format %s");

		service.create(format);

		Mockito.verify(formatRepository, Mockito.times(1)).persist(Mockito.any(GreetingFormatEntity.class));
	}

	@Test
	void createWithNullName() {
		final var format = new GreetingFormat(null,null, "Test format %s");

		Assertions.assertThrows(InvalidGreetingFormatException.class, () -> service.create(format));
	}

	@Test
	void findUnknown() {
		Assertions.assertEquals(Optional.empty(), service.findOne(UUID.randomUUID()));
	}

	@Test
	void findOne() {
		final var greetingFormat = new GreetingFormat(UUID.randomUUID(), "test", "Test format %s");
		final var entity = new GreetingFormatEntity();
		entity.setId(greetingFormat.id());
		entity.setName("test");
		entity.setFormat(greetingFormat.format());

		Mockito
				.when(formatRepository.findByIdOptional(greetingFormat.id()))
				.thenReturn(Optional.of(entity));

		Assertions.assertEquals(Optional.of(greetingFormat), service.findOne(greetingFormat.id()));
	}

	@Test
	void emptyFindAll() {
		@SuppressWarnings("unchecked") PanacheQuery<GreetingFormatEntity> mockedQuery = Mockito.mock(PanacheQuery.class);
		Mockito.when(formatRepository.findAll()).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.list()).thenReturn(new ArrayList<>());

		Assertions.assertEquals(0, service.findAll().size());
	}

	@Test
	void deleteNull() {
		service.delete(null);

		Mockito.verify(formatRepository, Mockito.times(0)).deleteById(null);
	}

	@Test
	void deleteUuid() {
		final var uuid = UUID.randomUUID();

		service.delete(uuid);

		Mockito.verify(formatRepository, Mockito.times(1)).deleteById(uuid);
	}

	@Test
	void updateFormat() throws InvalidGreetingFormatException {
		final var greetingFormat = new GreetingFormat(UUID.randomUUID(), "NEW", "Test new format %s");
		final var entity = new GreetingFormatEntity();
		entity.setId(greetingFormat.id());
		entity.setName("OLD");
		entity.setFormat("Test old format %s");

		Mockito
				.when(formatRepository.findByIdOptional(greetingFormat.id()))
				.thenReturn(Optional.of(entity));

		service.update(greetingFormat);

		Assertions.assertEquals(greetingFormat.format(), entity.getFormat());
		Assertions.assertEquals("OLD", entity.getName());
	}
}
