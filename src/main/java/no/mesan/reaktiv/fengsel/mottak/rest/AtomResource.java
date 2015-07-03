package no.mesan.reaktiv.fengsel.mottak.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import no.mesan.reaktiv.fengsel.mottak.atom.AtomFeedGenerator;
import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository;

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

    private final KontrollerteFangerRepository kontrollerteFangerRepository;
    private final AtomFeedGenerator atomFeedGenerator;

    public AtomResource(final KontrollerteFangerRepository kontrollerteFangerRepository) {
        this.kontrollerteFangerRepository = kontrollerteFangerRepository;
        this.atomFeedGenerator = new AtomFeedGenerator();
    }

    @GET
    @Path("/alt")
    @Timed
    public String hentAlleAtomTing() throws FeedException {
        final Feed newFeed = atomFeedGenerator.lagNyFeed("Fangemottak");
        final List<Fange> fanger = kontrollerteFangerRepository.hentAlleFanger();

        for (final Fange fange : fanger) {
            atomFeedGenerator.leggTilElement(newFeed, fange.getNavn(), fange.getId());
        }

        return atomFeedGenerator.hentFeedSomStreng(newFeed);
    }
}
