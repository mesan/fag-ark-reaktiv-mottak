package no.mesan.reaktiv.fengsel.mottak.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.dto.FangeDTO;
import no.mesan.reaktiv.fengsel.mottak.isolat.IsolatService;
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel;
import no.mesan.reaktiv.fengsel.mottak.logistikk.LogistikkService;
import no.mesan.reaktiv.fengsel.mottak.melding.EiendelerRegistrertMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.MetalldetektorMelding;
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Actor for å registrere en fanges eiendeler.
 *
 * @author Vidar Berentsen Folden
 */
public class MetalldetektorActor extends AbstractActor {

    public MetalldetektorActor() {
        IsolatService isolatService = new IsolatService();
        final LogistikkService logistikkService = new LogistikkService();

        receive(ReceiveBuilder
                        .match(NavnOgNrRegistrertMelding.class, fangeRegistrert -> {
                            System.out.println("Metalldetektor for: " + fangeRegistrert);

                            final Fange fange = fangeRegistrert.getFange();

                            // Vi simulerer virkeligheten med litt tilfeldig generering
                            List<Eiendel> kontraband = genererEiendeler();

                            // Eiendeler blir fratatt fange og sendt til logistikkservice
                            logistikkService.registrerEiendeler(fange, kontraband);

                            int alvorlighetsgrad = getAlvorlighetsgrad(kontraband, 3);

                            // Si i fra slik at man vet om fangen skal i isolat eller ikke
                            isolatService.settIIsolat(new FangeDTO(fange.getId(), fange.getNavn()), alvorlighetsgrad);
                            sender().tell(new MetalldetektorMelding(fange, alvorlighetsgrad), self());
                        })
                        .build());
    }

    private int getAlvorlighetsgrad(List<Eiendel> kontraband, int bound) {
        if(kontraband.size() > 0) {
            return (new Random()).nextInt(bound);
        }
        return 0;
    }

    /**
     * Genererer enten ingen eller noen eiendeler som fangen har gjemt
     * i kroppens hulrom.
     * @return Liste, tom eller fylt
     */
    private List<Eiendel> genererEiendeler() {
        if ((new Random()).nextInt(1) == 0) {
            return new ArrayList<>(0);
        }
        return Arrays.asList(
                new Eiendel("Rambokniv", "Våpen", "Knivstikkeenhet"),
                new Eiendel("Desert Eagle", "Våpen", "Skytevåpen"));
    }

    public static Props props() {
        return Props.create(MetalldetektorActor.class, MetalldetektorActor::new);
    }
}
