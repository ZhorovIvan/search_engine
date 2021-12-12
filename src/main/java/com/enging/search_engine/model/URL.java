package com.enging.search_engine.model;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "path_to_web_site", indexes = @Index(columnList = "path"))
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    @NotNull
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
    private String content;

    public URL(String path, int code, String content) {
        this.path = path;
        this.code = code;
        this.content = content;
    }


}
