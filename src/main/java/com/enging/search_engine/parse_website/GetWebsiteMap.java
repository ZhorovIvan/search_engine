package com.enging.search_engine.parse_website;
import com.enging.search_engine.model.page.Page;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.RecursiveTask;


/**
 * The class creates child tasks that move to the current link and get new unique links
 * */

public class GetWebsiteMap extends RecursiveTask<Collection<PageData>> {

    private final String url;
    private static String generalUrl;
    private static HashMap<String, PageData> urls;
    private static final int TIME_SHOUT_DOWN = 1;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; " +
                                        "en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private final static String REFERER = "http://www.google.com";
    private Document pageContent;


    public GetWebsiteMap(String url) {
        this.url = url;
        if (urls == null) {
            generalUrl = url;
            urls = new HashMap<>();
        }
        getInfoFromWebsite();
    }

    @Override
    protected Collection<PageData> compute() {
        try {
            HashSet<GetWebsiteMap> allURLTask = new HashSet<>();
            for (Element element : pageContent.select("a")) {
                String currentURL = element.absUrl("href");
                if (currentURL.matches(generalUrl + ".*.html") && !urls.containsKey(currentURL)) {
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

    private void getInfoFromWebsite(){
        try {
            Thread.sleep(TIME_SHOUT_DOWN);
            pageContent = getElementsFromWebSite();
            int statusCode = getCodeFromWebSite();
            urls.put(url, new PageData(url, statusCode, pageContent));
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Document getElementsFromWebSite() throws IOException {
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .referrer(REFERER)
                    .get();
        }catch (HttpStatusException he){
            return new Document("");
        }
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
