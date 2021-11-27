package com.enging.search_engine.entyty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "path_to_web_site", indexes = @Index(columnList = "path"))
public class PathWebSite {

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

}
