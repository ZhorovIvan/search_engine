package com.enging.search_engine.parse_website;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.RecursiveTask;


@ToString
public class MapOfWebsite extends RecursiveTask<HashSet<String>> {

    private final String url;
    private static HashSet<String> urls;
    private static final int TIME_SHOUT_DOWN = 500;
    private static final String GENERAL_URL = "http://www.playback.ru";


    public MapOfWebsite(String url) {
        if (urls == null) {
            urls = new HashSet<>();
        }
        urls.add(url);
        this.url = url;
    }

    @Override
    protected HashSet<String> compute() {
        try {
            Elements elements = getElementsFromWebSite();
            HashSet<MapOfWebsite> mapOfWebsites = new HashSet<>();
            for (Element element : elements) {
                String currentURL = element.absUrl("href");
                if (currentURL.matches(GENERAL_URL + ".*.html")&& !urls.contains(currentURL)) {
                    Thread.sleep(TIME_SHOUT_DOWN);
                    MapOfWebsite task = new MapOfWebsite(currentURL);
                    task.fork();
                    mapOfWebsites.add(task);
                }
            }

            for (MapOfWebsite mapOfWebsite: mapOfWebsites) {
                mapOfWebsite.join();
            }
            return urls;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Elements getElementsFromWebSite() throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; " +
                        "en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get();
        return doc.select("a");
    }

}
