package com.enging.search_engine.parse_website;
import com.enging.search_engine.model.URL;
import lombok.ToString;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.RecursiveTask;


/**
 * The class creates child tasks that move to the current link and get new unique links
 *
 * */

@ToString
public class GetWebsiteMap extends RecursiveTask<Collection<URL>> {

    private final String url;
    private static HashMap<String,URL> urls;
    private static final int TIME_SHOUT_DOWN = 1;
    private static final String GENERAL_URL = "http://www.playback.ru";
    private final static String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; " +
            "en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private final static String REFERER = "http://www.google.com";
    private Document pageContent;
    private int statusCode;

    public GetWebsiteMap(String url) {
        this.url = url;
        if (urls == null) {
            urls = new HashMap<>();
        }
        try {
            pageContent = getElementsFromWebSite();
            statusCode = getCodeFromWebSite();
            urls.put(url, new URL(url.replace(GENERAL_URL, ""), statusCode, pageContent.html()));
        }
         catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Collection<URL> compute() {
        try {
            HashSet<GetWebsiteMap> allURLTask = new HashSet<>();
            for (Element element : pageContent.select("a")) {
                String currentURL = element.absUrl("href");
                if (currentURL.matches(GENERAL_URL + ".*.html") && !urls.containsKey(currentURL)) {
                    GetWebsiteMap task = new GetWebsiteMap(currentURL);
                    task.fork();
                    allURLTask.add(task);
                }
            }
            for (GetWebsiteMap mapOfWebsite : allURLTask) {
                mapOfWebsite.join();
            }
            return urls.values();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document getElementsFromWebSite() throws IOException, InterruptedException {
        Thread.sleep(TIME_SHOUT_DOWN);
        return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .referrer(REFERER)
                .get();
    }

    private int getCodeFromWebSite() throws IOException {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .referrer(REFERER)
                    .execute();
            return response.statusCode();
        }catch (HttpStatusException he){
            return he.getStatusCode();
        }
    }

}
