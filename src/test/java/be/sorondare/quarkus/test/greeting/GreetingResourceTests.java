package be.sorondare.quarkus.test.greeting;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTests {

	@Test
	@TestSecurity(user = "user", roles = {"user"})
	void hello() {
		given()
				.when()
				.get("/greeting/hello")
				.then()
				.statusCode(200)
				.body(is("Hello Me"));
	}

	@Test
	void helloWithoutAuth() {
		given()
				.when()
				.get("/greeting/hello")
				.then()
				.statusCode(401);
	}

	@Test
	@TestSecurity(user = "user", roles = {"user"})
	void helloWithName() {
		final var name = "MyName";
		given()
				.queryParam("name", name)
				.when()
				.get("/greeting/hello")
				.then()
				.statusCode(200)
				.body(is("Hello " + name));
	}

	@Test
	@TestSecurity(user = "user", roles = {"user"})
	void helloWithNameAndFormat() {
		final var name = "MyName";
		given()
				.queryParam("name", name)
				.queryParam("format", "hello_en")
				.when()
				.get("/greeting/hello")
				.then()
				.statusCode(200)
				.body(is("Hi " + name));
	}
}
