package no.mesan.reaktiv.fengsel.mottak.fangeregister;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;

import retrofit.RestAdapter;

/**
 * Service for å manipulere fanger i fangeregisteret.
 *
 * @author Christian Ihle
 */
public class FangeregisterService {

    private final FangeregisterRestService fangeregisterRestService;

    public FangeregisterService() {
        // Går mot service fra https://github.com/mesan/fag-ark-persistering-fangeregister
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:49000")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        fangeregisterRestService = restAdapter.create(FangeregisterRestService.class);
    }

    public Fange lagreFange(final String fangenavn) {
        final FangeDTO fangeDTO = fangeregisterRestService.opprettFange(new FangeDTO(fangenavn, null));

        return new Fange(fangeDTO.getNavn(), fangeDTO.getId());
    }
}
