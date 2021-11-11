package be.sorondare.quarkus.test.greeting.format;

import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper
interface GreetingFormatMapper {
	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "format", source = "format")
	})
	GreetingFormat toDto(SimpleGreetingFormat source);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "format", source = "source.format")
	})
	GreetingFormat toDto(UUID id, SimpleGreetingFormat source);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "format", source = "format")
	})
	GreetingFormat toDto(GreetingFormatEntity entity);

	List<GreetingFormat> toDtos(List<GreetingFormatEntity> entities);

	@InheritInverseConfiguration
	GreetingFormatEntity toEntity(GreetingFormat dto);

	@InheritInverseConfiguration
	GreetingFormatEntity toEntity(GreetingFormat dto, @MappingTarget GreetingFormatEntity entity);
}
