package no.mesan.reaktiv.fengsel.mottak.isolat;

import no.mesan.reaktiv.fengsel.mottak.dto.FangeDTO;
import no.mesan.reaktiv.fengsel.mottak.logistikk.EiendelListeDTO;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * REST-service for å fange til isolat.
 *
 */
public interface IsolatRestService {

    @GET("/isolat/FangeTilIsolat/{fange}/IsoleringsTid/{itid}/CallbackUrl/{url}/Method/GET")
    Response settIIsolat(@Path("fange") FangeDTO fange,
                              @Path("itid") Integer isoleringstid,
                              @Path("url") String callbackURL);
}

/*
Eks:
{
        "FangeTilIsolat": {
        "Id": "1ES532KD1",
        "Navn": "Albert Åbert"
        },
        "IsoleringsTid": 5,
        "CallbackUrl": "http://dummy.url/",
        "Method": "GET"
        }
*/