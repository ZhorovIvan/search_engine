package com.enging.search_engine.model.lemma;

import com.enging.search_engine.model.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LemmaService {

    @Autowired
    private LemmaRepository lemmaRepository;

    public void saveAllDate(Iterable<Lemma> websiteDate){
        lemmaRepository.saveAll(websiteDate);
    }

}
