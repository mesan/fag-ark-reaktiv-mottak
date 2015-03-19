package no.mesan.reaktiv.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.mesan.reaktiv.helloworld.health.TemplateHealthCheck;
import no.mesan.reaktiv.helloworld.resources.HelloWorldResource;

/**
 * Starts the server.
 *
 * App url: http://localhost:8080/hello-world
 * Metrics url: http://localhost:8081/
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	public static void main(final String[] args) throws Exception {
		new HelloWorldApplication().run(new String[] {"server", "hello-world.yml"});
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(final Bootstrap<HelloWorldConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(final HelloWorldConfiguration configuration, final Environment environment) {
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(), configuration.getDefaultName());

		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}
}
