package no.mesan.reaktiv.fengsel.mottak.logistikk;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO med en liste av en fanges eiendeler.
 *
 * @author Christian Ihle
 */
public class EiendelListeDTO {

    private final List<EiendelDTO> eiendel;

    public EiendelListeDTO(final List<EiendelDTO> eiendel) {
        this.eiendel = eiendel;
    }

    public List<EiendelDTO> getEiendel() {
        return eiendel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("eiendel", eiendel)
                .toString();
    }
}
