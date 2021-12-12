package com.enging.search_engine.model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends CrudRepository<URL, Integer> {

}
