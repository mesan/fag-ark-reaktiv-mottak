package no.mesan.reaktiv.fengsel.mottak.service;

import static no.mesan.reaktiv.fengsel.mottak.actor.Actorer.REGISTRERE_EIENDELER;
import static no.mesan.reaktiv.fengsel.mottak.actor.Actorer.REGISTRERE_NAVN_OG_NR;

import no.mesan.reaktiv.fengsel.mottak.actor.FangemottakActor;
import no.mesan.reaktiv.fengsel.mottak.actor.RegistrerEiendelerActor;
import no.mesan.reaktiv.fengsel.mottak.actor.RegistrerNavnOgNrActor;
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Service som setter i gang mottaksregistrering av en fange.
 *
 * @author Christian Ihle
 */
public class FangemottakService {

    private final ActorRef fangemottak;

    public static void main(final String[] args) {
        // For test
        new FangemottakService().mottaFange("Navid Floska");
    }

    public FangemottakService() {
        final ActorSystem akka = ActorSystem.create("mottak");

        // Initialiserer actor som styrer prosess
        fangemottak = akka.actorOf(FangemottakActor.props());

        // Initialiserer actorer som utfører arbeidet
        akka.actorOf(RegistrerNavnOgNrActor.props(), REGISTRERE_NAVN_OG_NR.navn());
        akka.actorOf(RegistrerEiendelerActor.props(), REGISTRERE_EIENDELER.navn());
    }

    /**
     * Start her. Denne metoden setter i gang mottak av en fange til fengselet.
     *
     * @param fangenavn Navn på ankommet fange.
     */
    public void mottaFange(final String fangenavn) {
        fangemottak.tell(new FangeMottattMelding(fangenavn), ActorRef.noSender());
    }
}
