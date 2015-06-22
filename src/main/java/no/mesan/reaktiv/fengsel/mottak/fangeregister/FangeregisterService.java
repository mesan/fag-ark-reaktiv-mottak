package no.mesan.reaktiv.fengsel.mottak.fangeregister;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.dto.FangeDTO;
import no.mesan.reaktiv.fengsel.mottak.service.LogLevelVelgerService;

import retrofit.RestAdapter;

/**
 * Service for å manipulere fanger i fangeregisteret.
 *
 * @author Christian Ihle
 */
public class FangeregisterService {

    private final FangeregisterRestService fangeregisterRestService;

    public FangeregisterService() {
        final LogLevelVelgerService logLevelVelgerService = new LogLevelVelgerService();

        // Går mot service fra https://github.com/mesan/fag-ark-persistering-fangeregister
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:49000")
                .setLogLevel(logLevelVelgerService.velgLogLevel())
                .build();

        fangeregisterRestService = restAdapter.create(FangeregisterRestService.class);
    }

    public Fange lagreFange(final String fangenavn) {
        final FangeDTO fangeTilSending = new FangeDTO(fangenavn, null);

        System.out.println("FangeregisterService - Registrerer fange i fangeregister rest-tjeneste: " + fangeTilSending);
        final FangeDTO opprettetFange = fangeregisterRestService.opprettFange(fangeTilSending);

        return new Fange(opprettetFange.getNavn(), opprettetFange.getId());
    }
}
