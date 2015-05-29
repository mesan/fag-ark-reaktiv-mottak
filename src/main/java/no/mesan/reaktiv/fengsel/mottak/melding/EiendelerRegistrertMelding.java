package no.mesan.reaktiv.fengsel.mottak.melding;

import java.io.Serializable;
import java.util.List;

import no.mesan.reaktiv.fengsel.mottak.Fange;
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Melding for å si fra at en fange har fått eiendelene sine registrert.
 *
 * @author Christian Ihle
 */
public class EiendelerRegistrertMelding implements Serializable {

    public static final long serialVersionUID = 1;

    private final Fange fange;
    private final List<Eiendel> eiendeler;

    public EiendelerRegistrertMelding(final Fange fange, final List<Eiendel> eiendeler) {
        this.fange = fange;
        this.eiendeler = eiendeler;
    }

    public Fange getFange() {
        return fange;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fange", fange)
                .append("eiendeler", eiendeler)
                .toString();
    }
}
