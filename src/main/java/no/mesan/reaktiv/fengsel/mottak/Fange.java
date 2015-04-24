package no.mesan.reaktiv.fengsel.mottak;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Representasjon av en fange.
 *
 * @author Christian Ihle
 */
public class Fange implements Serializable {

    public static final long serialVersionUID = 1;

    private final String navn;
    private final Integer fangenummer;

    public Fange(final String navn, final Integer fangenummer) {
        this.navn = navn;
        this.fangenummer = fangenummer;
    }

    public String getNavn() {
        return navn;
    }

    public Integer getFangenummer() {
        return fangenummer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("fangenummer", fangenummer)
                .toString();
    }
}
