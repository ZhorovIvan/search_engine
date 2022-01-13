package com.enging.search_engine.parse_website;

import com.enging.search_engine.lemmatizer.CounterWord;
import com.enging.search_engine.model.field.Field;
import com.enging.search_engine.model.field.FieldService;
import com.enging.search_engine.model.index.Index;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

@Component
public class UploadToMySQL {

    @Autowired
    private FieldService fieldService;
    private final static int SUM_CORES = Runtime.getRuntime().availableProcessors();
    private ArrayList<Index> indexTable = new ArrayList<>();
    private HashMap<String, Integer> dataLemmaTable = new HashMap<>();

    public void run(String url) {
        try {
            //temporarily
            fieldService.saveAllData(new ArrayList<>() {{
                add(new Field("title", "title", 1.0f));
                add(new Field("body", "body", 0.8f));
            }});
            //-------------

            indexPage(getAllWebsitePages(url));


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void indexPage(ArrayList<PageData> pages) {
        for (int numberCore = 1; numberCore <= SUM_CORES; numberCore++) {
            int startPosition = pages.size() / SUM_CORES * (numberCore - 1);
            int endPosition = (numberCore == SUM_CORES) ? (pages.size() / SUM_CORES * numberCore) : pages.size();
            new Thread(() -> {
                workWithDataFrame(pages.subList(startPosition, endPosition));
            });
        }
    }

    private void workWithDataFrame(List<PageData> pages) {
        try {
            for (PageData data : pages) {
                if (data.getCode() == 404 || data.getCode() == 500) {
                    continue;
                }
                countLemmas(data);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void countLemmas(PageData data) throws IOException {
        List<Field> allField = getFieldData();
        for (Field field : allField) {
            Elements element = data.getPageData().getElementsByTag(field.getSelector());
            HashMap<String, Integer> lemmas = CounterWord.counterRussianWords(element.text());
        }

    }

    private void addDataToLemmaTable(Set<String> lemmas){
        lemmas.forEach(lemma -> {
            dataLemmaTable.put(lemma, dataLemmaTable.getOrDefault(lemma, 0));
        });
    }

    private List<Field> getFieldData() {
        try {
            List<Field> allFields = fieldService.getAllData();
            if (allFields == null) {
                throw new IOException("Table field is empty");
            }
            return allFields;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<PageData> getAllWebsitePages(String url) {
        Collection<PageData> urls = new ForkJoinPool()
                .invoke(new GetWebsiteMap(url));
        return new ArrayList<>(urls);
    }

}
