package no.mesan.reaktiv.fengsel.mottak.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import no.mesan.reaktiv.fengsel.mottak.actor.FangemottakActor;
import no.mesan.reaktiv.fengsel.mottak.actor.MetalldetektorActor;
import no.mesan.reaktiv.fengsel.mottak.actor.RegistrerEiendelerActor;
import no.mesan.reaktiv.fengsel.mottak.actor.RegistrerNavnOgNrActor;
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding;
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static no.mesan.reaktiv.fengsel.mottak.actor.Actorer.*;

/**
 * Service som setter i gang mottaksregistrering av en fange.
 *
 * @author Christian Ihle
 */
public class FangemottakService {

    private final Logger logger = LoggerFactory.getLogger(FangemottakService.class);

    private final ActorRef fangemottak;

    public FangemottakService(final KontrollerteFangerRepository kontrollerteFangerRepository) {
        final ActorSystem akka = ActorSystem.create("mottak");

        // Initialiserer actor som styrer prosess
        fangemottak = akka.actorOf(FangemottakActor.props(kontrollerteFangerRepository));

        // Initialiserer actorer som utfører arbeidet
        akka.actorOf(RegistrerNavnOgNrActor.props(), REGISTRERE_NAVN_OG_NR.navn());
        akka.actorOf(RegistrerEiendelerActor.props(), REGISTRERE_EIENDELER.navn());
        akka.actorOf(MetalldetektorActor.props(), METALLDETEKTOR.navn());
    }

    /**
     * Start her. Denne metoden setter i gang mottak av en fange til fengselet.
     *
     * @param fangenavn Navn på ankommet fange.
     */
    public void mottaFange(final String fangenavn) {
        logger.info("Fange har ankommet mottaket: {}", fangenavn);
        fangemottak.tell(new FangeMottattMelding(fangenavn), ActorRef.noSender());
    }
}
