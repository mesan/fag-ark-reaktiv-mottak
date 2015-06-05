package no.mesan.reaktiv.helloworld.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Integrasjonstest av {@link MottakRestService}.
 *
 * @author Christian Ihle
 */
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
        final Response response = mottakRestService.startFangemottak(new FangeDto("Arne", "Bjarne"));

        // 204: the server has fulfilled the request but does not need to return an entity-body
        assertEquals(204, response.getStatus());
    }
}
