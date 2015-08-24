package no.mesan.reaktiv.fengsel.mottak.actor;

/**
 * Liste over actorer som kan slås opp, og adressen til de.
 *
 * @author Christian Ihle
 */
public enum Actorer {

    REGISTRERE_NAVN_OG_NR("RegistrerNavnOgNrActor", "/user/RegistrerNavnOgNrActor"),
    REGISTRERE_EIENDELER("RegistrerEiendelerActor", "/user/RegistrerEiendelerActor"),
    METALLDETEKTOR("MetalldetektorActor", "/user/MetalldetektorActor");

    private final String navn;
    private final String adresse;

    Actorer(final String navn, final String adresse) {
        this.navn = navn;
        this.adresse = adresse;
    }

    public String navn() {
        return navn;
    }

    public String adresse() {
        return adresse;
    }
}
