package com.enging.search_engine.lemmatizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect("http://www.playback.ru")
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; " +
                        "en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get();

        Elements element = document.getElementsByTag("body");
        System.out.println(element.text());
    }
}
