package no.mesan.reaktiv.fengsel.mottak;

import org.joox.Match;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import static org.joox.JOOX.*;

public class RullebladTest {
    @Test
    public void testIt() throws IOException, SAXException {
        Document document = $(new File("src/main/resources/rulleblad.xml")).document();

        // voldsforbrytere som er ustabile bør holdes unna vanlige fanger
        $(document).find("rulleblad")
                .filter(ctx -> $(ctx).children("dommer").children("dom").attr("type").equals("vold"))
                .filter(ctx -> $(ctx).children("psyke").content().equals("ustabil"))
                .append("<avdeling>farlig</avdeling>"); // "append" legger på dette for hver voldsforbryter

        // sexualforbrytere får bo sammen med ustabile voldsmenn: de trenger noe å avreagere på?
        $(document).find("dom")
                .filter(ctx -> $(ctx).attr("type").equals("sexovergrep"))
                .parent().parent()
                .append("<avdeling>farlig</avdeling>");

        // Restrerende fordeles annenhver på avdeling A og avdeling B
        Match resten = $(document).find("rulleblad")
                .filter(ctx -> $(ctx).children("avdeling").isEmpty());

        resten.filter(even()).append("<avdeling>A</avdeling>");

        resten.filter(odd()).append("\n<avdeling>B</avdeling>");

        System.out.println($(document).toString());
    }
}
