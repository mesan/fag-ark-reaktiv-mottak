package no.mesan.reaktiv.helloworld.resources;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import no.mesan.reaktiv.fengsel.mottak.FangemottakService;
import no.mesan.reaktiv.helloworld.domain.Saying;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.validation.Validated;

/**
 * Rest-tjeneste for mottak av fanger
 *
 * @author Svein Melby
 */
@Path("/mottak/")
@Consumes(value = MediaType.APPLICATION_JSON)
public class MottakResource {
    private final String template;
    private final FangemottakService fangemottakService;

    public MottakResource(final String template,
                          final FangemottakService fangemottakService) {
        this.template = template;
        this.fangemottakService = fangemottakService;
    }

    @POST
    @Timed
    public void startFangemottak(@Valid final FangeDto fange) {
        fangemottakService.mottaFange(fange.getFulltNavn());
    }
}
