package com.enging.search_engine;

import com.enging.search_engine.parse_website.MapOfWebsite;
import com.enging.search_engine.parse_website.UploadToMySQL;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class SearchEngineApplication {


    private static final int SUM_CORES = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) {
        //SpringApplication.run(SearchEngineApplication.class, args);
        long s = System.currentTimeMillis();

        HashSet<String> urls = new ForkJoinPool().invoke(new MapOfWebsite("http://www.playback.ru"));
        for (int core = 0; core < SUM_CORES; core++){
            new Thread(new UploadToMySQL(urls, core)).start();
        }
        System.out.println(System.currentTimeMillis() - s + " - " + urls.size());
    }

}
