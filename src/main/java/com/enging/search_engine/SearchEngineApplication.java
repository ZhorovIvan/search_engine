package com.enging.search_engine;
import com.enging.search_engine.parse_website.UploadToMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class SearchEngineApplication implements CommandLineRunner {

    @Autowired
    private UploadToMySQL uploadToMySQL;

    public static void main(String[] args) {
        SpringApplication.run(SearchEngineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        uploadToMySQL.run("http://www.playback.ru");
    }
}
