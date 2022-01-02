package com.enging.search_engine.lemmatizer;
import java.io.IOException;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) throws IOException {

        HashMap<String, Integer> s = CounterWord.counterRussianWords("Повторное появление леопарда в Осетии позволяет предположить, что " +
                "леопард постоянно обитает в некоторых районах Северного Кавказа. ");
        s.keySet().forEach(x -> System.out.println(s.get(x) + " - " + x));

    }
}
