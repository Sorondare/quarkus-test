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
	@Transactional
	public GreetingFormat create(String format) throws EmptyFormatException {
		if (format == null) {
			throw new EmptyFormatException();
		}

		GreetingFormatEntity entity = new GreetingFormatEntity(format);
		formatRepository.persist(entity);
		return mapper.toDto(entity);
	}

	@Override
	@Transactional
	public void update(GreetingFormat format) throws EmptyFormatException {
		if (format == null || format.id() == null) {
			throw new EmptyFormatException();
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
