package no.mesan.reaktiv.fengsel.mottak.melding;

import java.io.Serializable;

import no.mesan.reaktiv.fengsel.mottak.Fange;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Melding for å si fra at en fange er registrert med navn og nummer.
 *
 * @author Christian Ihle
 */
public class NavnOgNrRegistrertMelding implements Serializable {

    public static final long serialVersionUID = 1;

    private final Fange fange;

    public NavnOgNrRegistrertMelding(final Fange fange) {
        this.fange = fange;
    }

    public Fange getFange() {
        return fange;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fange", fange)
                .toString();
    }
}
