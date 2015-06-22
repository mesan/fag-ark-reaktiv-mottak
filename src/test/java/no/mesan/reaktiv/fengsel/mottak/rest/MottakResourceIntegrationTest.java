package no.mesan.reaktiv.fengsel.mottak.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Integrasjonstest av {@link MottakRestService}.
 *
 * @author Christian Ihle
 */
@Ignore
public class MottakResourceIntegrationTest {

    private MottakRestService mottakRestService;

    @Before
    public void setUp() {
        // Går mot service fra https://github.com/mesan/fag-ark-reaktiv-mottak
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:8080")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mottakRestService = restAdapter.create(MottakRestService.class);
    }

    @Test
    public void startFangemottak() {
        final Response response = mottakRestService.startFangemottak(new NyFangeDTO("Arne", "Bjarne"));

        // 204: the server has fulfilled the request but does not need to return an entity-body
        assertEquals(204, response.getStatus());
    }

    @Test
    public void startFangemottakMedMasseFolk() throws IOException, URISyntaxException {
        final List<String> alleFornavn = hentAlleNavn("fornavn.txt");
        final List<String> alleEtternavn = hentAlleNavn("etternavn.txt");

        for (int i = 0; i < 100; i++) {
            final String fornavn = velgTilfeldigNavn(alleFornavn);
            final String etternavn = velgTilfeldigNavn(alleEtternavn);
            final NyFangeDTO fange = new NyFangeDTO(fornavn, etternavn);

            final Response response = mottakRestService.startFangemottak(fange);
            assertEquals(204, response.getStatus());
        }
    }

    private List<String> hentAlleNavn(final String fil) throws URISyntaxException, IOException {
        final URL resource = getClass().getClassLoader().getResource(fil);
        assertNotNull(resource);

        final Path path = Paths.get(resource.toURI());

        return Files.readAllLines(path);
    }

    private String velgTilfeldigNavn(final List<String> navn) {
        return navn.get(velgTilfeldigTall(0, navn.size() -1));
    }

    private int velgTilfeldigTall(final int min, final int max) {
        final int range = (max - min) + 1;

        return (int)(Math.random() * range) + min;
    }
}
