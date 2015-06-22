package no.mesan.reaktiv.fengsel.mottak.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import no.mesan.reaktiv.fengsel.mottak.service.FangemottakService;

import com.codahale.metrics.annotation.Timed;

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
    public void startFangemottak(@Valid final NyFangeDTO fange) {
        fangemottakService.mottaFange(fange.getFulltNavn());
    }
}
