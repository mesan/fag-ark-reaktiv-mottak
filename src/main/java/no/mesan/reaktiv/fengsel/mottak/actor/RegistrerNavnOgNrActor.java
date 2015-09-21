package no.mesan.reaktiv.fengsel.mottak.actor;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.fangeregister.FangeregisterService;
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

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public RegistrerNavnOgNrActor() {
        final FangeregisterService fangeregisterService = new FangeregisterService();

        receive(ReceiveBuilder
                        .match(FangeMottattMelding.class, fangeMottatt -> {
                            log.info("Registrerer fange: {}", fangeMottatt);

                            final Fange lagretFange = fangeregisterService.lagreFange(fangeMottatt.getFangenavn());
                            sender().tell(new NavnOgNrRegistrertMelding(lagretFange), self());
                        })
                        .build());
    }

    public static Props props() {
        return Props.create(RegistrerNavnOgNrActor.class, RegistrerNavnOgNrActor::new);
    }
}
