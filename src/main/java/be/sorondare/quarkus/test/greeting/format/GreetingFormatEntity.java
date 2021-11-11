package be.sorondare.quarkus.test.greeting.format;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "greeting_format")
@Getter
@Setter
@NoArgsConstructor
class GreetingFormatEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "format")
	private String format;

	GreetingFormatEntity(String format) {
		this.format = format;
	}
}
