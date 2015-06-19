package no.mesan.reaktiv.fengsel.mottak.domene;

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
    private final String id;

    public Fange(final String navn, final String id) {
        this.navn = navn;
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("id", id)
                .toString();
    }
}
