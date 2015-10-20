package no.mesan.reaktiv.fengsel.mottak.service;

import org.joox.Match;
import org.w3c.dom.Document;

import java.util.stream.Collectors;

import static org.joox.JOOX.*;

/**
 * Behandler rullebladet som kommer i xml-form.
 */
public class RullebladService {

    /** Voldsforbrytere som er ustabile bør holdes unna vanlige fanger. */
    public RullebladService plasserUstabileVoldsmenn(Document document) {
        $(document).find("rulleblad")
                .filter(ctx -> $(ctx).children("dommer").children("dom").attr("type").equals("vold"))
                .filter(ctx -> $(ctx).children("psyke").content().equals("ustabil"))
                .append("<avdeling>farlig</avdeling>"); // "append" legger på dette for hver voldsforbryter

        return this;
    }

    /** Sexualforbrytere får bo sammen med ustabile voldsmenn. */
    public RullebladService plasserSexualforbrytere(Document document) {
        $(document).find("dom")
                .filter(ctx -> $(ctx).attr("type").equals("sexovergrep"))
                .parent()
                .after("<avdeling>farlig</avdeling>");

        return this;
    }

    /**
     * Restrerende fordeles annenhver på avdeling A og avdeling B.
     * OBS. Må kalles etter at alle spesifikke avdelinger er ferdig fordelt,
     * da denne ikke gjør noen utvalgskriterier annet enn å hente de som ikke
     * har fått avdeling ennå.
     */
    public RullebladService plasserResterendeFanger(Document document) {
        Match resten = $(document).find("rulleblad")
                .filter(ctx -> $(ctx).children("avdeling").isEmpty());

        resten.filter(even()).append("<avdeling>A</avdeling>");

        resten.filter(odd()).append("<avdeling>B</avdeling>");

        return this;
    }

    public String eksporterCSVString(Document document, String avdeling) {
        return $(document).find("rulleblad")
                .filter(ctx -> $(ctx).children("avdeling").content().equals(avdeling))
                .map(ctx -> $(ctx).attr("id"))
                .stream()
                .collect(Collectors.joining(";"));
    }
}
