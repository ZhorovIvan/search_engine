package com.enging.search_engine;

import com.enging.search_engine.entyty.PathWebSite;
import com.enging.search_engine.parse_website.MapOfWebsite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class SearchEngineApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SearchEngineApplication.class, args);
        System.out.println("sadsdasdasdasdad.sda.html".matches(".*(html)?[^#]"));

        MapOfWebsite mapOfWebsite = new MapOfWebsite("http://www.playback.ru/");
        String s = new ForkJoinPool().invoke(mapOfWebsite);
        System.out.println(s);
    }

}
