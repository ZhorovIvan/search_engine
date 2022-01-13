package com.enging.search_engine.model.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public void saveAllDate(Iterable<Page> websiteDate){
        pageRepository.saveAll(websiteDate);
    }


}
