package no.mesan.reaktiv.fengsel.mottak.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import no.mesan.reaktiv.fengsel.mottak.melding.EiendelerRegistrertMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding;
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository;

import static no.mesan.reaktiv.fengsel.mottak.actor.Actorer.*;

/**
 * Actor som har ansvar for å styre hele prosessen rundt fangemottak.
 *
 * @author Christian Ihle
 */
public class FangemottakActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public FangemottakActor(final KontrollerteFangerRepository kontrollerteFangerRepository) {
        final ActorSelection registrerNavnOgNrActor = context().actorSelection(REGISTRERE_NAVN_OG_NR.adresse());
        final ActorSelection registrerEiendelerActor = context().actorSelection(REGISTRERE_EIENDELER.adresse());
        final ActorSelection metalldetektorActor = context().actorSelection(METALLDETEKTOR.adresse());

        receive(ReceiveBuilder
                        // Steg 1: registrere navn og nummer
                        .match(FangeMottattMelding.class, fangeMottatt -> {
                            logger.info("Starter fangeregistrering: {}", fangeMottatt);
                            registrerNavnOgNrActor.tell(fangeMottatt, self());
                        })
                        // Steg 2: registrere eiendeler
                        .match(NavnOgNrRegistrertMelding.class, navnOgNrRegistrertMelding -> {
                            logger.info("Starter registrering av navn og nr: {}", navnOgNrRegistrertMelding);
                            registrerEiendelerActor.tell(navnOgNrRegistrertMelding, self());
                        })
                        // Steg 3: gå til metalldetektor
                        .match(EiendelerRegistrertMelding.class, eiendelerRegistrertMelding -> {
                            logger.info("Starter registrering av eiendeler: {}", eiendelerRegistrertMelding);
                            metalldetektorActor.tell(eiendelerRegistrertMelding, self());
                        })
                        .build());
    }

    public static Props props(final KontrollerteFangerRepository kontrollerteFangerRepository) {
        return Props.create(FangemottakActor.class, kontrollerteFangerRepository);
    }
}
