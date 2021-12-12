package com.enging.search_engine.parse_website;
import com.enging.search_engine.model.URL;
import com.enging.search_engine.model.URLRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class UploadToMySQL implements Runnable {

    @Autowired
    private URLRepository urlRepository;
    private final ArrayList<String> urls;
    private final int numberCore;

    public UploadToMySQL(HashSet<String> urls, int numberCore) {
        this.urls = new ArrayList<>(urls);
        this.numberCore = numberCore;

    }

    @Override
    public void run() {
        try {
            int startPosition = 0;
            int endPosition = 2;
            ArrayList<URL> webSateData = new ArrayList<>();
            for (int i = startPosition; i < endPosition; i++) {
                String[] info = getElementsFromWebSite(urls.get(i));
                webSateData.add(new URL(urls.get(i), Integer.parseInt(info[0]), info[1]));
            }
            urlRepository.saveAll(webSateData);
        } catch (IOException | NullPointerException e) {
            e.getMessage();
        }
    }


    private String[] getElementsFromWebSite(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; " +
                        "en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get();


        return null;

    }


}
