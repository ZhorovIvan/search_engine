package com.enging.search_engine.lemmatizer;
import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.WrongCharaterException;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CounterWord {

    private final static String SPLIT_TEXT_BY = "[ !?:;,.-]+";
    private final static String RUSSIAN_SYMBOL = "[А-Яа-я]+";
    private final static String CHECK_PATH_SPEECH = "(МЕЖД|СОЮЗ|ПРЕДЛ|ЧАСТ)";

    public static HashMap<String, Integer> counterRussianWords(String text) throws IOException {

        HashMap<String, Integer> counter = new HashMap<>();
        String[] words = text.split(SPLIT_TEXT_BY);
        LuceneMorphology luceneMorph = new RussianLuceneMorphology();
        for (String word : words) {
            //Check it's russian word or not
            if (!word.matches(RUSSIAN_SYMBOL)) {
                continue;
            }
            List<String> wordWithFullForm = luceneMorph.getMorphInfo(word.toLowerCase(Locale.ROOT));
            String[] fullFormWord = wordWithFullForm.get(0).split(" ");
            //Definite path of speech
            if (!fullFormWord[1].matches(CHECK_PATH_SPEECH)) {
                String wordBaseForms = luceneMorph.getNormalForms(word.toLowerCase(Locale.ROOT)).get(0);
                counter.put(wordBaseForms, counter.getOrDefault(wordBaseForms, 0) + 1);
            }
        }
        return counter;
    }

}
