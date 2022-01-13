package com.enging.search_engine.model.index;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "index_db")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int page_id;

    @Getter
    @Setter
    private int lemma_id;

    @Getter
    @Setter
    private float rank_i;

    public Index(int page_id, int lemma_id, float rank) {
        this.page_id = page_id;
        this.lemma_id = lemma_id;
        this.rank_i = rank;
    }

}
