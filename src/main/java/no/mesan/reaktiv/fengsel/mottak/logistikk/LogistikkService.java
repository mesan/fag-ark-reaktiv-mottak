package no.mesan.reaktiv.fengsel.mottak.logistikk;

import java.util.List;
import java.util.stream.Collectors;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;

import retrofit.RestAdapter;

/**
 * Service for å jobbe med en fanges eiendeler.
 */
public class LogistikkService {

    private final LogistikkRestService logistikkRestService;

    public LogistikkService() {
        // Går mot service fra https://github.com/mesan/fag-ark-reaktiv-logistikk
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:9999")
                .setLogLevel(RestAdapter.LogLevel.FULL)
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

        logistikkRestService.leggTilEiendeler(fange.getId(), eiendelListe);
    }
}
