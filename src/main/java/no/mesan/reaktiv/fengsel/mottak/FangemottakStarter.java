package no.mesan.reaktiv.fengsel.mottak;

import no.mesan.reaktiv.fengsel.mottak.helse.TemplateHealthCheck;
import no.mesan.reaktiv.fengsel.mottak.rest.AtomResource;
import no.mesan.reaktiv.fengsel.mottak.rest.MottakResource;
import no.mesan.reaktiv.fengsel.mottak.service.FangemottakService;
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Starts the server.
 *
 * App url: http://localhost:8080/mottak/
 * Atom url: http://localhost:8080/atom/alt
 * Metrics url: http://localhost:8081/
 */
public class FangemottakStarter extends Application<FangemottakConfig> {

    public static void main(final String[] args) throws Exception {
        new FangemottakStarter().run(new String[]{"server", "fangemottak.yml"});
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

        final KontrollerteFangerRepository kontrollerteFangerRepository = new KontrollerteFangerRepository();

        final FangemottakService fangemottakService = new FangemottakService(kontrollerteFangerRepository);
        final MottakResource mottakResource =
                new MottakResource(
                        configuration.getTemplate(),
                        fangemottakService);

        final AtomResource atomResource = new AtomResource(kontrollerteFangerRepository);

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(mottakResource);
        environment.jersey().register(atomResource);
    }
}
