package com.enging.search_engine;

import com.enging.search_engine.entyty.PathWebSite;
import com.enging.search_engine.parse_website.MapOfWebsite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class SearchEngineApplication {

    private static final int cores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        //SpringApplication.run(SearchEngineApplication.class, args);
        ForkJoinPool pool = new ForkJoinPool(cores);
        HashSet<String> urls = pool.invoke(new MapOfWebsite("http://www.playback.ru"));

    }

}
