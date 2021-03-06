package no.mesan.reaktiv.fengsel.mottak.isolat;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.dto.FangeDTO;
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel;
import no.mesan.reaktiv.fengsel.mottak.logistikk.EiendelDTO;
import no.mesan.reaktiv.fengsel.mottak.logistikk.EiendelListeDTO;
import no.mesan.reaktiv.fengsel.mottak.logistikk.LogistikkRestService;
import no.mesan.reaktiv.fengsel.mottak.service.LogLevelVelgerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RestAdapter;
import retrofit.http.Path;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for å sette en fange i isolat.
 */
public class IsolatService {

    private final Logger logger = LoggerFactory.getLogger(IsolatService.class);

    private final IsolatRestService isolatRestService;

    public IsolatService() {
        final LogLevelVelgerService logLevelVelgerService = new LogLevelVelgerService();

        // Går mot service fra https://github.com/mesan/fag-ark-reaktiv-isolat
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localdocker:9998")
                .setLogLevel(logLevelVelgerService.velgLogLevel())
                .build();

        isolatRestService = restAdapter.create(IsolatRestService.class);
    }

    public void settIIsolat(FangeDTO fange,
                                 Integer isoleringstid) {
        logger.info("Sender {} isolat rest-tjeneste i antall dager: {}",
                                         fange, isoleringstid);
        isolatRestService.settIIsolat(fange, isoleringstid, "http://vg.no");
    }
}
