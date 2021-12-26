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

	@Column(name = "name", updatable = false, nullable = false, unique = true)
	private String name;

	@Column(name = "format")
	private String format;

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : (name != null ? name.hashCode() : 31);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GreetingFormatEntity other) || (id == null && name == null)) {
			return false;
		}

		return id != null ? id.equals(other.id) : name.equals(other.name);
	}

	@Override
	public String toString() {
		return "GreetingFormatEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", format='" + format + '\'' +
				'}';
	}
}
