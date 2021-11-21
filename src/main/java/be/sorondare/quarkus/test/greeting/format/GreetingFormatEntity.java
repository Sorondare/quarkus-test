package be.sorondare.quarkus.test.greeting.format;

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

	@Override
	public int hashCode() {
		return (31 + (id == null ? 0 : id.hashCode())) % 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GreetingFormatEntity other) || id == null) {
			return false;
		}

		return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "GreetingFormatEntity{" +
				"id=" + id +
				", format='" + format + '\'' +
				'}';
	}
}
