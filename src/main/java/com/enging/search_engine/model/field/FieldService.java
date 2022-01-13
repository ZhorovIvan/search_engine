package com.enging.search_engine.model.field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public void saveAllData(Iterable<Field> websiteDate){
        fieldRepository.saveAll(websiteDate);
    }

    public List<Field> getAllData(){
        return fieldRepository.findAll();
    }
}
