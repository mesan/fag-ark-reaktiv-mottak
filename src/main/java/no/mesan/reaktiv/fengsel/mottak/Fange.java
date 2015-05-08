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
    private final Integer id;

    public Fange(final String navn, final Integer id) {
        this.navn = navn;
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("fangenummer", id)
                .toString();
    }
}
