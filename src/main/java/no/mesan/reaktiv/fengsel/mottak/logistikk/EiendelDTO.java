package no.mesan.reaktiv.fengsel.mottak.logistikk;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO for en eiendel knyttet til en fange.
 */
public class EiendelDTO {

    private final String navn;
    private final String tekniskBeskrivelse;
    private final String beskrivelse;

    public EiendelDTO(final String navn, final String tekniskBeskrivelse, final String beskrivelse) {
        this.navn = navn;
        this.tekniskBeskrivelse = tekniskBeskrivelse;
        this.beskrivelse = beskrivelse;
    }

    public String getNavn() {
        return navn;
    }

    public String getTekniskBeskrivelse() {
        return tekniskBeskrivelse;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("tekniskBeskrivelse", tekniskBeskrivelse)
                .append("beskrivelse", beskrivelse)
                .toString();
    }
}
