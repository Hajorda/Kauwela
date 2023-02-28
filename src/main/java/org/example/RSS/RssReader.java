package org.example.RSS;



import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;

public class RssReader {
   private String url ;
    private SyndFeed feed;
    private String description;
    private String docs;
    private String Author;
    private String title;
    private String latestAnime;
    private String AnimeLink;


    public RssReader(String url) throws FeedException, IOException {
        this.url =url;
       feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
       description = feed.getDescription();
       docs=feed.getDocs();
       Author=feed.getAuthor();

       title=feed.getTitle();
       latestAnime = feed.getEntries().get(0).getTitle();
       AnimeLink = feed.getEntries().get(0).getLink();

    }

    public String getDescription() {
        return description;
    }

    public String getDocs() {
        return docs;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return title;
    }

    public String getAnimeLink() {
        return AnimeLink;
    }

    public String getLatestAnime() {
        return latestAnime;
    }
}
