package be.sorondare.quarkus.test.greeting.format;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class GreetingFormatRepository implements PanacheRepositoryBase<GreetingFormatEntity, UUID> {
	Optional<GreetingFormatEntity> findOneByName(String name) {
		return find("name", name).firstResultOptional();
	}
}
