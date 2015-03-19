package no.mesan.reaktiv.fengsel.mottak;

import java.io.Serializable;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

/**
 * Akka-service for å registrere navn og nummer på en fange.
 *
 * @author Christian Ihle
 */
public class RegistrereNavnOgNrService {

    public static void main(final String[] args) {
        new RegistrereNavnOgNrService().registrerNavnOgNr();
    }

    public void registrerNavnOgNr() {
        final ActorSystem system = ActorSystem.create("RegistrereNavnOgNummer");
        final ActorRef navnActor = system.actorOf(Props.create(RegistrerNavnActor.class), "navn");

        navnActor.tell(new Fange("Navid Floska", 43543L), ActorRef.noSender());
    }

    private static class Fange implements Serializable {

        public static final long serialVersionUID = 1;

        private final String navn;
        private final Long fangenummer;

        public Fange(final String navn, final Long fangenummer) {
            this.navn = navn;
            this.fangenummer = fangenummer;
        }
    }

    private static class RegistrerNavnActor extends AbstractActor {
        public RegistrerNavnActor() {
            final ActorRef nummerActor = context().actorOf(Props.create(RegistrerNummerActor.class), "nummer");

            receive(ReceiveBuilder
                            .match(Fange.class, fange -> {
                                System.out.println("Registrerer fange: " + fange.navn);
                                nummerActor.tell(fange, self());
                            })
                            .build());
        }
    }

    private static class RegistrerNummerActor extends AbstractActor {
        public RegistrerNummerActor() {
            receive(ReceiveBuilder
                            .match(Fange.class, fange -> {
                                System.out.println("Registrerer fangenummer på " + fange.navn + ": " + fange.fangenummer);
                                // Dette ser ikke ut til å ha noen effekt
                                //self().tell(PoisonPill.getInstance(), self());
                            })
                            .build());
        }
    }
}
