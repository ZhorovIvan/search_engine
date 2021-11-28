package com.enging.search_engine.parse_website;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.RecursiveTask;


@ToString
public class MapOfWebsite extends RecursiveTask<String> {

    private static HashSet<String> urls;
    private String url;

    public MapOfWebsite(String url) {
        if (urls == null) {
            urls = new HashSet<>();
        }
        this.url = url;
    }



    @Override
    protected String compute() {
        try {
            StringBuilder test = new StringBuilder();
            Elements elements = getElementsFromWebSite();
            ArrayList<MapOfWebsite> mapOfWebsites = new ArrayList<>();
            for (Element element : elements) {
                Thread.sleep(500);
                if (!urls.contains(element.absUrl("href")) &&
                        element.absUrl("href").matches("http://www.playback.ru" + ".*.html")) {
                    System.out.println(element.absUrl("href"));
                    urls.add(element.absUrl("href"));
                    MapOfWebsite task = new MapOfWebsite(element.absUrl("href"));
                    task.fork();
                    mapOfWebsites.add(task);
                }

            }
            for (MapOfWebsite mapOfWebsite : mapOfWebsites) {
                test.append(mapOfWebsite.join());
            }

            //urls.forEach(System.out::println);
            return test.toString();
        } catch (
                Exception e) {
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
