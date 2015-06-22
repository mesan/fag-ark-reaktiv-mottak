package no.mesan.reaktiv.fengsel.mottak.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Representasjon av en fange fra tjenesten fangeregister.
 *
 * @author Christian Ihle
 */
public class FangeDTO {

    private final String navn;
    private final String id;

    public FangeDTO(final String navn, final String id) {
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
