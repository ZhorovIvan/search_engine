package com.enging.search_engine.model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    public void saveAllDate(Iterable<URL> websiteDate){
        urlRepository.saveAll(websiteDate);
    }


}
