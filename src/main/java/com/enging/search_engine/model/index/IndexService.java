package com.enging.search_engine.model.index;

import com.enging.search_engine.model.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    private IndexRepository indexRepository;

    public void saveAllDate(Iterable<Index> websiteDate){
        indexRepository.saveAll(websiteDate);
    }

}
