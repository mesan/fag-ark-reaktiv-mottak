package no.mesan.reaktiv.fengsel.mottak;

import no.mesan.reaktiv.fengsel.mottak.service.RullebladService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import static org.joox.JOOX.$;

public class RullebladTest {
    RullebladService service;

    @Before
    public void setup() {
        service = new RullebladService();
    }

    @Test
    public void testAll() throws IOException, SAXException {
        Document document = $(new File("src/main/resources/rulleblad.xml")).document();

        service.plasserUstabileVoldsmenn(document)
                .plasserSexualforbrytere(document)
                .plasserResterendeFanger(document);

        doAsserts(document);

        // System.out.println($(document).toString());

        String alleA = service.eksporterCSVString(document, "A");
        Assert.assertEquals("04027212345", alleA.split(";")[0]);

        //sendTilAvdelingA(alleA);
//        System.out.println(alleA);
    }

    @Test
    public void testRekkefolgeSexForst() throws IOException, SAXException {
        Document document = $(new File("src/main/resources/rulleblad.xml")).document();
        service.plasserSexualforbrytere(document)
                .plasserUstabileVoldsmenn(document)
                .plasserResterendeFanger(document);

        doAsserts(document);
    }

    @Test
    public void testRekkefolgeBlirFeilOmResterendeErForst() throws IOException, SAXException {
        Document document = $(new File("src/main/resources/rulleblad.xml")).document();
        service.plasserResterendeFanger(document)
                .plasserSexualforbrytere(document)
                .plasserUstabileVoldsmenn(document);

        //System.out.println($(document).toString());
        // Havner i feil avdeling fordi resterende har satt avd på alt.
        // Kan være kandidat til idiotsikring i fremtiden, men kjipt å
        // måtte lage en if-setning. Navnestandard "resterende" får duge så lenge.
        Assert.assertNotEquals("farlig", $(document).find("avdeling").content(0));
    }

    private void doAsserts(Document document) {
        Assert.assertEquals("farlig", $(document).find("avdeling").content(0));
        Assert.assertEquals("farlig", $(document).find("avdeling").content(1));
        Assert.assertEquals("A", $(document).find("avdeling").content(2));
        Assert.assertEquals("B", $(document).find("avdeling").content(3));
        Assert.assertFalse($(document).find("avdeling").isEmpty());
    }
}
