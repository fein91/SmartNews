package com.smartnews.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Folder.TABLE_NAME)
@NamedQueries({@NamedQuery(name = Folder.FIND_ALL, query = "select f from Folder f")})
public class Folder implements ModelEntity {
    public static final String TABLE_NAME = "folder";
    public static final String FIND_ALL = "Folder.findAll";
    private static final String SEQUENCE_GENERATOR = "folder_seq_gen";
    private static final String SEQUENCE_NAME = "folder_seq";
    private static final String PARENT_FK = "parent_fk";
    private static final String PARENT_FOLDER = "parentFolder";
    private static final String FOLDER_FK = "folder_fk";
    private static final String ARTICLE_REFERENCED_COLUMN = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize=1)
    private long id;
    @ManyToOne
    @JoinColumn(name = PARENT_FK)
    private Folder parentFolder;
    @OneToMany(mappedBy= PARENT_FOLDER)
    public List<Folder> subFolders;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = FOLDER_FK, referencedColumnName = ARTICLE_REFERENCED_COLUMN)
    private List<Article> articles;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
