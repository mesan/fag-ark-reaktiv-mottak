package no.mesan.reaktiv.fengsel.mottak.actor;

import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

/**
 * Actor for å registrere en fanges eiendeler.
 *
 * @author Christian Ihle
 */
public class RegistrerEiendelerActor extends AbstractActor {

    public RegistrerEiendelerActor() {
        receive(ReceiveBuilder
                        .match(NavnOgNrRegistrertMelding.class, fangeRegistrert -> {
                            System.out.println("RegistrerEiendelerActor - Registrerer fange: " + fangeRegistrert);
                            // TODO kalle rest
                            // TODO kalle sender med resultat
                        })
                        .build());
    }

    public static Props props() {
        return Props.create(RegistrerEiendelerActor.class, RegistrerEiendelerActor::new);
    }
}
