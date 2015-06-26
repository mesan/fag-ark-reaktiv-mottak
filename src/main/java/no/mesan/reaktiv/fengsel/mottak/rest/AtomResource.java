package no.mesan.reaktiv.fengsel.mottak.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import no.mesan.reaktiv.fengsel.mottak.atom.AtomFeedGenerator;

import com.codahale.metrics.annotation.Timed;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.io.FeedException;

/**
 * Rest-tjeneste for levering av atom feeds.
 *
 * @author Christian Ihle
 */
@Path("/atom")
@Produces(value = MediaType.APPLICATION_ATOM_XML)
public class AtomResource {

    private final AtomFeedGenerator atomFeedGenerator;

    public AtomResource() {
        atomFeedGenerator = new AtomFeedGenerator();
    }

    @GET
    @Path("/alt")
    @Timed
    public String hentAlleAtomTing() throws FeedException {
        // TODO litt bedre data...
        final Feed newFeed = atomFeedGenerator.lagNyFeed("Fangemottak");
        atomFeedGenerator.leggTilElement(newFeed, "Fange nr 1");
        atomFeedGenerator.leggTilElement(newFeed, "Fange nr 2");

        return atomFeedGenerator.hentFeedSomStreng(newFeed);
    }
}
