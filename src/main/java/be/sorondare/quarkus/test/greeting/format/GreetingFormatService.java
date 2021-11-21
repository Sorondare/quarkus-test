package be.sorondare.quarkus.test.greeting.format;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GreetingFormatService {
	List<GreetingFormat> findAll();
	Optional<GreetingFormat> findOne(UUID id);
	Optional<GreetingFormat> findOne(String name);
	GreetingFormat create(GreetingFormat format) throws InvalidGreetingFormatException;
	void update(GreetingFormat format) throws InvalidGreetingFormatException;
	void delete(UUID id);
}
