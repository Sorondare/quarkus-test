package be.sorondare.quarkus.test.greeting.format;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
class GreetingFormatRepository implements PanacheRepositoryBase<GreetingFormatEntity, UUID> {
}
