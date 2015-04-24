package no.mesan.reaktiv.fengsel.mottak.melding;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Melding for å si at en fange har ankommet fengselet.
 *
 * @author Christian Ihle
 */
public class FangeMottattMelding implements Serializable {

    public static final long serialVersionUID = 1;

    private final Integer fangenummer;

    public FangeMottattMelding(final Integer fangenummer) {
        this.fangenummer = fangenummer;
    }

    public Integer getFangenummer() {
        return fangenummer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fangenummer", fangenummer)
                .toString();
    }
}
