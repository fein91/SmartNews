package com.smartnews.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Tag.TABLE_NAME)
@NamedQueries({@NamedQuery(name = Tag.FIND_ALL, query = "select f from Tag f")})
public class Tag implements ModelEntity {
    public static final String FIND_ALL = "Tag.findAll";
    public static final String TABLE_NAME = "tag";
    private static final String SEQUENCE_GENERATOR = "tag_seq_gen";
    private static final String SEQUENCE_NAME = "tag_seq";
    private static final String ARTICLE_MAPPED_BY = "tags";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize=1)
    private long id;

    @ManyToMany(mappedBy = ARTICLE_MAPPED_BY)
    private List<Article> articles;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
