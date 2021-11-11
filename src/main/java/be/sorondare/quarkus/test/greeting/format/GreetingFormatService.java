package be.sorondare.quarkus.test.greeting.format;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GreetingFormatService {
	List<GreetingFormat> findAll();
	Optional<GreetingFormat> findOne(UUID id);
	GreetingFormat create(String format);
	void update(GreetingFormat format);
	void delete(UUID id);
}
