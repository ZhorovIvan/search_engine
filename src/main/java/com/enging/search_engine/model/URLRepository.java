package com.enging.search_engine.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, Integer> {
}
