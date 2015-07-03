package no.mesan.reaktiv.fengsel.mottak.repository;

import java.util.ArrayList;
import java.util.List;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;

import com.google.common.collect.ImmutableList;

/**
 * In-memory "database" med kontrollerte fanger.
 *
 * @author Christian Ihle
 */
public class KontrollerteFangerRepository {

    private final List<Fange> fanger;

    public KontrollerteFangerRepository() {
        fanger = new ArrayList<>();
    }

    public void leggTilFange(final Fange fange) {
        System.out.println("KontrollerteFangerRepository - legger til fange: " + fange);
        fanger.add(fange);
    }

    public List<Fange> hentAlleFanger() {
        return ImmutableList.copyOf(fanger);
    }
}
