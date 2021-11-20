package be.sorondare.quarkus.test.greeting.format;

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
	void create() throws EmptyFormatException {
		final var format = "Test format %s";

		Assertions.assertEquals(format, service.create(format).getFormat());
		Mockito.verify(formatRepository, Mockito.times(1)).persist(Mockito.any(GreetingFormatEntity.class));
	}

	@Test
	void findUnknown() {
		Assertions.assertEquals(Optional.empty(), service.findOne(UUID.randomUUID()));
	}

	@Test
	void findOne() {
		final var greetingFormat = GreetingFormat.builder().id(UUID.randomUUID()).format("Test format %s").build();
		final var entity = new GreetingFormatEntity();
		entity.setId(greetingFormat.getId());
		entity.setFormat(greetingFormat.getFormat());

		Mockito
				.when(formatRepository.findByIdOptional(greetingFormat.getId()))
				.thenReturn(Optional.of(entity));

		Assertions.assertEquals(Optional.of(greetingFormat), service.findOne(greetingFormat.getId()));
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
	void updateFormat() throws EmptyFormatException {
		final var greetingFormat = GreetingFormat.builder().id(UUID.randomUUID()).format("Test new format %s").build();
		final var entity = new GreetingFormatEntity();
		entity.setId(greetingFormat.getId());
		entity.setFormat("Test old format %s");

		Mockito
				.when(formatRepository.findByIdOptional(greetingFormat.getId()))
				.thenReturn(Optional.of(entity));

		service.update(greetingFormat);

		Assertions.assertEquals(greetingFormat.getFormat(), entity.getFormat());
	}
}
