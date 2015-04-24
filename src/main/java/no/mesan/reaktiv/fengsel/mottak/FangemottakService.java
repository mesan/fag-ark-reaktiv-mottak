package no.mesan.reaktiv.fengsel.mottak;

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

    private final ActorRef ruter;

    public static void main(final String[] args) {
        new FangemottakService().mottaFange(123);
    }

    public FangemottakService() {
        final ActorSystem akka = ActorSystem.create("mottak");

        ruter = akka.actorOf(FangemottakActor.props());

        akka.actorOf(RegistrerNavnOgNrActor.props(), REGISTRERE_NAVN_OG_NR.navn());
        akka.actorOf(RegistrerEiendelerActor.props(), REGISTRERE_EIENDELER.navn());
    }

    public void mottaFange(final Integer fangenummer) {
        ruter.tell(new FangeMottattMelding(fangenummer), ActorRef.noSender());
    }
}
