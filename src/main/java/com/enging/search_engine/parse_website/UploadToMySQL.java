package com.enging.search_engine.parse_website;
import com.enging.search_engine.model.URL;
import com.enging.search_engine.model.URLService;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

@Component
public class UploadToMySQL {

    @Autowired
    private URLService urlService;
    private static final String GENERAL_URL = "http://www.playback.ru";

    public void run() {
        try {
            urlService.saveAllDate(getAllURLs());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<URL> getAllURLs(){
        Collection<URL> urls = new ForkJoinPool()
                .invoke(new GetWebsiteMap(GENERAL_URL));
        return new ArrayList<>(urls);
    }




}
