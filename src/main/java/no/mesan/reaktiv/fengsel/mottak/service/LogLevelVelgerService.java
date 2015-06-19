package no.mesan.reaktiv.fengsel.mottak.service;

import retrofit.RestAdapter;

/**
 * Service for å velge loglevel.
 *
 * @author Christian Ihle
 */
public class LogLevelVelgerService {

    private final Debug debug;

    public LogLevelVelgerService() {
        debug = new Debug();
    }

    public RestAdapter.LogLevel velgLogLevel() {
        if (debug.erAktivert()) {
            return RestAdapter.LogLevel.FULL;
        }

        return RestAdapter.LogLevel.BASIC;
    }
}
