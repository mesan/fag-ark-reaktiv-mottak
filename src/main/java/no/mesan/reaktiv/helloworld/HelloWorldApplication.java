package no.mesan.reaktiv.helloworld;

import no.mesan.reaktiv.fengsel.mottak.FangemottakService;
import no.mesan.reaktiv.fengsel.mottak.health.TemplateHealthCheck;
import no.mesan.reaktiv.helloworld.resources.MottakResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Starts the server.
 *
 * App url: http://localhost:8080//mottak/
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

		final FangemottakService fangemottakService = new FangemottakService();
		final MottakResource mottakResource =
				new MottakResource(
				configuration.getTemplate(),
				fangemottakService);

		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(mottakResource);
	}
}
