package no.mesan.reaktiv.fengsel.mottak.fangeregister;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * REST-service for å manipulere fanger i fangeregisteret.
 *
 * @author Christian Ihle
 */
public interface FangeregisterRestService {

    @POST("/fanger")
    FangeDTO opprettFange(@Body FangeDTO fangeDTO);
}
