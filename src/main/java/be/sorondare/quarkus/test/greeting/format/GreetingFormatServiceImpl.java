package be.sorondare.quarkus.test.greeting.format;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class GreetingFormatServiceImpl implements GreetingFormatService {

	private final GreetingFormatRepository formatRepository;
	private final GreetingFormatMapper mapper;

	@Inject
	GreetingFormatServiceImpl(GreetingFormatRepository formatRepository, GreetingFormatMapper mapper) {
		this.formatRepository = formatRepository;
		this.mapper = mapper;
	}

	@Override
	public List<GreetingFormat> findAll() {
		return mapper.toDtos(
				formatRepository
						.findAll()
						.list()
		);
	}

	@Override
	public Optional<GreetingFormat> findOne(UUID id) {
		return id == null ? Optional.empty() : formatRepository
				.findByIdOptional(id)
				.map(mapper::toDto);
	}

	@Override
	public Optional<GreetingFormat> findOne(String name) {
		return name == null ? Optional.empty() : formatRepository
				.findOneByName(name)
				.map(mapper::toDto);
	}

	@Override
	@Transactional
	public GreetingFormat create(GreetingFormat format) throws InvalidGreetingFormatException {
		if (format == null || format.name() == null) {
			throw new InvalidGreetingFormatException();
		}

		GreetingFormatEntity entity = mapper.toEntity(format);
		formatRepository.persist(entity);
		return mapper.toDto(entity);
	}

	@Override
	@Transactional
	public void update(GreetingFormat format) throws InvalidGreetingFormatException {
		if (format == null || format.id() == null) {
			throw new InvalidGreetingFormatException();
		}

		formatRepository
				.findByIdOptional(format.id())
				.map(entity -> mapper.toEntity(format, entity))
				.map(mapper::toDto);
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		if (id == null) {
			return;
		}

		formatRepository.deleteById(id);
	}
}
