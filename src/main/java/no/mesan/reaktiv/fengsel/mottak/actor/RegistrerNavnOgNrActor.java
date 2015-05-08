package no.mesan.reaktiv.fengsel.mottak.actor;

import no.mesan.reaktiv.fengsel.mottak.Fange;
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

/**
 * Actor for å registrere navn og nummer på en fange.
 *
 * @author Christian Ihle
 */
public class RegistrerNavnOgNrActor extends AbstractActor {

    public RegistrerNavnOgNrActor() {
        receive(ReceiveBuilder
                        .match(FangeMottattMelding.class, fangeMottatt -> {
                            System.out.println("RegistrerNavnOgNrActor - Registrerer fange: " + fangeMottatt);
                            // TODO kalle rest
                            final Fange fange = new Fange(fangeMottatt.getFangenavn(), "123");
                            sender().tell(new NavnOgNrRegistrertMelding(fange), self());
                        })
                        .build());
    }

    public static Props props() {
        return Props.create(RegistrerNavnOgNrActor.class, RegistrerNavnOgNrActor::new);
    }
}
