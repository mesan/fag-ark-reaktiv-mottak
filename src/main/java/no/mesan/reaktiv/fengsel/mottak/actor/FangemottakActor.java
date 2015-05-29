package no.mesan.reaktiv.fengsel.mottak.actor;

import static no.mesan.reaktiv.fengsel.mottak.actor.Actorer.*;

import no.mesan.reaktiv.fengsel.mottak.melding.EiendelerRegistrertMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

/**
 * Actor som har ansvar for å styre hele prosessen rundt fangemottak.
 *
 * @author Christian Ihle
 */
public class FangemottakActor extends AbstractActor {

    public FangemottakActor() {
        final ActorSelection registrerNavnOgNrActor = context().actorSelection(REGISTRERE_NAVN_OG_NR.adresse());
        final ActorSelection registrerEiendelerActor = context().actorSelection(REGISTRERE_EIENDELER.adresse());

        receive(ReceiveBuilder
                        // Steg 1: registrere navn og nummer
                        .match(FangeMottattMelding.class, fangeMottatt -> {
                            System.out.println("FangemottakActor - " + fangeMottatt);
                            registrerNavnOgNrActor.tell(fangeMottatt, self());
                        })
                        // Steg 2: registrere eiendeler
                        .match(NavnOgNrRegistrertMelding.class, navnOgNrRegistrertMelding -> {
                            System.out.println("FangemottakActor - " + navnOgNrRegistrertMelding);
                            registrerEiendelerActor.tell(navnOgNrRegistrertMelding, self());
                        })
                        // Steg 3: gå til metalldetektor
                        .match(EiendelerRegistrertMelding.class, eiendelerRegistrertMelding -> {
                            System.out.println("FangemottakActor - " + eiendelerRegistrertMelding);
                            // TODO
                        })
                        .build());
    }

    public static Props props() {
        return Props.create(FangemottakActor.class, FangemottakActor::new);
    }
}
