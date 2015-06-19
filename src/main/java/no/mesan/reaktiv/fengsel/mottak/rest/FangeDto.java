package no.mesan.reaktiv.fengsel.mottak.rest;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * TODO
 *
 * @author Svein Melby
 */
public class FangeDto {

    @NotBlank
    private String fornavn;

    @NotNull
    private String etternavn;

    public FangeDto() {
    }

    public FangeDto(@Nonnull final String fornavn,
                    @Nonnull final String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(final String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(final String etternavn) {
        this.etternavn = etternavn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(
                this,
                ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fornavn", fornavn)
                .append("etternavn", etternavn)
                .toString();
    }

    public String getFulltNavn() {
        return this.fornavn + " " + this.etternavn;
    }
}
