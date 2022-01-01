package com.enging.search_engine.model;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(indexes = @Index(columnList = "path"))
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NotNull
    private String path;

    @Setter
    @Getter
    @NotNull
    private int code;

    @Getter
    @Setter
    @NotNull
    @Column(columnDefinition = " MEDIUMTEXT")
    private String content;

    public URL(String path, int code, String content) {
        this.path = path;
        this.code = code;
        this.content = content;
    }

}
