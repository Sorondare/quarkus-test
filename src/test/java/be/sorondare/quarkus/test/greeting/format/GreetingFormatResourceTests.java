package be.sorondare.quarkus.test.greeting.format;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingFormatResourceTests {

	@Test
	@TestSecurity(user = "admin", roles = {"admin"})
	void listFormat() {
		given()
				.when()
				.get("/greeting/format")
				.then()
				.statusCode(200)
				.assertThat()
				.body("size()", is(2));
	}

	@Test
	@TestSecurity(user = "admin", roles = {"user"})
	void unauthorizedListFormat() {
		given()
				.when()
				.get("/greeting/format")
				.then()
				.statusCode(403);
	}

	@Test
	@TestSecurity(user = "admin", roles = {"admin"})
	void postAndDeleteNewFormat() {
		final var newFormat = new SimpleGreetingFormat("test", "Test format %s");

		final var greetingFormat = given()
				.contentType(ContentType.JSON)
				.body(newFormat)
				.when()
				.post("/greeting/format")
				.then()
				.statusCode(201)
				.extract()
				.body()
				.as(GreetingFormat.class);

		given()
				.pathParam("id", greetingFormat.id())
				.delete("/greeting/format/{id}")
				.then()
				.statusCode(204);
	}
}
