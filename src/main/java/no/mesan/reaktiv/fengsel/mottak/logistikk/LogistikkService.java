package no.mesan.reaktiv.fengsel.mottak.logistikk;

import java.util.List;
import java.util.stream.Collectors;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.service.LogLevelVelgerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RestAdapter;

/**
 * Service for å jobbe med en fanges eiendeler.
 *
 * @author Christian Ihle
 */
public class LogistikkService {

    private final Logger logger = LoggerFactory.getLogger(LogistikkService.class);

    private final LogistikkRestService logistikkRestService;

    public LogistikkService() {
        final LogLevelVelgerService logLevelVelgerService = new LogLevelVelgerService();

        // Går mot service fra https://github.com/mesan/fag-ark-reaktiv-logistikk
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:9999")
                .setLogLevel(logLevelVelgerService.velgLogLevel())
                .build();

        logistikkRestService = restAdapter.create(LogistikkRestService.class);
    }

    public void registrerEiendeler(final Fange fange, final List<Eiendel> eiendeler) {
        final List<EiendelDTO> eiendelDTOer = eiendeler.stream()
                .map(eiendel -> new EiendelDTO(eiendel.getNavn(),
                                               eiendel.getTekniskBeskrivelse(),
                                               eiendel.getBeskrivelse()))
                .collect(Collectors.toList());

        final EiendelListeDTO eiendelListe = new EiendelListeDTO(eiendelDTOer);

        logger.info("Sender {} sine eiendeler til logistikk rest-tjeneste: {}",
                                         fange, eiendelListe);
        logistikkRestService.leggTilEiendeler(fange.getId(), eiendelListe);
    }
}
