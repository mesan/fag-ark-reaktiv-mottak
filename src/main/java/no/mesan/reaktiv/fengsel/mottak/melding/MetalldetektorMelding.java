package no.mesan.reaktiv.fengsel.mottak.melding;

import no.mesan.reaktiv.fengsel.mottak.domene.Fange;
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Melding fra metalldetektor.
 *
 * Inneholder fange og informasjon om hvor alvorlig brudd på reglene
 * fangen har begått.
 *
 * Created by vidarb on 22.06.2015.
 */
public class MetalldetektorMelding {
    public static final long serialVersionUID=1;
    private final Fange fange;
    private final int alvorlighetsgrad;

    public MetalldetektorMelding(final Fange fange, final int alvorlighetsgrad) {
        this.fange = fange;
        this.alvorlighetsgrad = alvorlighetsgrad;
    }

    public Fange getFange() {
        return fange;
    }

    public int getAlvorlighetsgrad() {
        return alvorlighetsgrad;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fange", fange)
                .toString();
    }
}
