package be.sorondare.quarkus.test.greeting;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@StaticInitSafe
@ConfigMapping(prefix = "be.sorondare.greeting")
public interface GreetingConfig {
	@WithName("format")
	@WithDefault("FORMAT NOT DEFINED FOR %s")
	String defaultFormat();

	@WithDefault("NAME_NOT_DEFINED")
	String defaultName();
}
