package com.enging.search_engine.parse_website;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

@AllArgsConstructor
public class PageData {

    @Setter
    @Getter
    private String url;

    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private Document pageData;

}
