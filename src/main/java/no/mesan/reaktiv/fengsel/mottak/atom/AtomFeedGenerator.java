package no.mesan.reaktiv.fengsel.mottak.atom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedOutput;

/**
 * Service for å lage atom feeds.
 */
public class AtomFeedGenerator {

    public Feed lagNyFeed(final String tittel) {
        final Feed feed = new Feed();

        feed.setFeedType("atom_1.0");
        feed.setTitle(tittel);
        feed.setId(genererUnikId());
        feed.setUpdated(lagDato());

        return feed;
    }

    public Entry leggTilElement(final Feed feed, final String tittel) {
        final Entry entry = new Entry();

        entry.setTitle(tittel);
        entry.setId(genererUnikId());
        entry.setUpdated(lagDato());

        leggElementIFeed(feed, entry);

        return entry;
    }

    public String hentFeedSomStreng(final Feed feed) {
        final WireFeedOutput wfo = new WireFeedOutput();

        try {
            return wfo.outputString(feed);
        }

        catch (final FeedException e) {
            throw new RuntimeException(e);
        }
    }

    private void leggElementIFeed(final Feed feed, final Entry entry) {
        List<Entry> entries = feed.getEntries();

        if (entries == null) {
            entries = new ArrayList<>();
        }

        entries.add(entry);
        feed.setEntries(entries);
    }

    private String genererUnikId() {
        return UUID.randomUUID().toString();
    }

    private Date lagDato() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        final String RFC3339DateString = sdf.format(new Date());

        try {
            return (Date) sdf.parseObject(RFC3339DateString);
        }

        catch (final ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
