package be.sorondare.quarkus.test.greeting.format;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GreetingFormatService {
	List<GreetingFormat> findAll();
	Optional<GreetingFormat> findOne(UUID id);
	GreetingFormat create(String format) throws EmptyFormatException;
	void update(GreetingFormat format) throws EmptyFormatException;
	void delete(UUID id);
}
