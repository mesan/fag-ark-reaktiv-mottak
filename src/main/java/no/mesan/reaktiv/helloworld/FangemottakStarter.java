package no.mesan.reaktiv.helloworld;

import no.mesan.reaktiv.fengsel.mottak.FangemottakService;
import no.mesan.reaktiv.fengsel.mottak.health.TemplateHealthCheck;
import no.mesan.reaktiv.fengsel.mottak.resources.MottakResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Starts the server.
 *
 * App url: http://localhost:8080//mottak/
 * Metrics url: http://localhost:8081/
 */
public class FangemottakStarter extends Application<FangemottakConfig> {

	public static void main(final String[] args) throws Exception {
		new FangemottakStarter().run(new String[] {"server", "hello-world.yml"});
	}

	@Override
	public String getName() {
		return "fangemottak";
	}

	@Override
	public void initialize(final Bootstrap<FangemottakConfig> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(final FangemottakConfig configuration, final Environment environment) {
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
