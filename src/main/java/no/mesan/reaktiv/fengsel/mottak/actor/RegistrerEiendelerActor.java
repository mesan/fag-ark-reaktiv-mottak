package no.mesan.reaktiv.fengsel.mottak.actor;

import java.util.Arrays;
import java.util.List;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel;
import no.mesan.reaktiv.fengsel.mottak.logistikk.LogistikkService;
import no.mesan.reaktiv.fengsel.mottak.melding.EiendelerRegistrertMelding;
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

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public RegistrerEiendelerActor() {
        final LogistikkService logistikkService = new LogistikkService();

        receive(ReceiveBuilder
                        .match(NavnOgNrRegistrertMelding.class, fangeRegistrert -> {
                            log.info("Registrerer eiendeler: {}", fangeRegistrert);

                            final Fange fange = fangeRegistrert.getFange();
                            final List<Eiendel> eiendeler = genererEiendeler();

                            logistikkService.registrerEiendeler(fange, eiendeler);
                            sender().tell(new EiendelerRegistrertMelding(fange, eiendeler), self());
                        })
                        .build());
    }

    // TODO kunne vært litt mer spennende og tilfeldig
    private List<Eiendel> genererEiendeler() {
        return Arrays.asList(
                new Eiendel("Rambokniv", "Våpen", "Knivstikkeenhet"),
                new Eiendel("Desert Eagle", "Våpen", "Skytevåpen"));
    }

    public static Props props() {
        return Props.create(RegistrerEiendelerActor.class, RegistrerEiendelerActor::new);
    }
}
