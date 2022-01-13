package com.enging.search_engine.model.field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String selector;

    @Getter
    @Setter
    private Float weight;

    public Field(String name, String selector, Float weight) {
        this.name = name;
        this.selector = selector;
        this.weight = weight;
    }

}
