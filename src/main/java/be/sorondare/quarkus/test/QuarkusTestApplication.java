package be.sorondare.quarkus.test;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

/**
 * Application class.
 *
 * Only define to customize openapi data.
 */

@SuppressWarnings("unused")
@OpenAPIDefinition(
		info = @Info(
				title="Quarkus test API",
				version = "0.0.1",
				contact = @Contact(
						name = "Support",
						url = "https://github.com/Sorondare/quarkus-test"),
				license = @License(
						name = "AGPL v3.0",
						url = "https://github.com/Sorondare/quarkus-test/blob/master/LICENSE"))
)
public class QuarkusTestApplication extends Application {
}
