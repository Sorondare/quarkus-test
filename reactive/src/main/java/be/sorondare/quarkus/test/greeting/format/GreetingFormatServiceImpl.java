package be.sorondare.quarkus.test.greeting.format;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GreetingFormatServiceImpl implements GreetingFormatService {
	private final List<GreetingFormat> formats;

	public GreetingFormatServiceImpl() {
		formats = Arrays.asList(
				new GreetingFormat(UUID.randomUUID(), "test", "Here is the test from %s"),
				new GreetingFormat(UUID.randomUUID(), "hello", "Hello %s")
		);
	}

	@Override
	public List<GreetingFormat> findAll() {
		return formats;
	}

	@Override
	public Optional<GreetingFormat> findOne(UUID id) {
		return formats
				.stream()
				.filter(format -> format.id().equals(id))
				.findFirst();
	}

	@Override
	public Optional<GreetingFormat> findOne(String name) {
		return formats
				.stream()
				.filter(format -> format.name().equals(name))
				.findFirst();
	}

	@Override
	public GreetingFormat create(GreetingFormat format) {
		formats.add(format);
		return format;
	}

	@Override
	public void update(GreetingFormat format) {
		GreetingFormat internal = findOne(format.id()).orElseThrow(NotFoundException::new);
		int index = formats.indexOf(internal);
		formats.set(index, format);
	}

	@Override
	public void delete(UUID id) {
		formats.removeIf(format -> format.id().equals(id));
	}
}
