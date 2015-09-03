package com.smartnews.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Article.TABLE_NAME)
@NamedQueries({@NamedQuery(name = Article.FIND_ALL, query = "select f from Article f")})
public class Article implements ModelEntity {
    public static final String FIND_ALL = "Article.findAll";
    public static final String TABLE_NAME = "article";
    private static final String SEQUENCE_GENERATOR = "article_seq_gen";
    private static final String SEQUENCE_NAME = "article_seq";
    private static final String FOLDER_FK = "folder_fk";
    private static final String TAG_JOINT_TABLE_NAME = "article_tag";
    private static final String TAG_JOINT_COLUMN_NAME = "article_fk";
    private static final String TAG_JOINT_REFERENCED_COLUMN = "id";
    private static final String TAG_INVERSE_JOINT_COLUMN_NAME = "tag_fk";
    private static final String TAG_INVERSE_JOINT_REFERENCED_COLUMN = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize=1)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = FOLDER_FK)
    private Folder folder;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = TAG_JOINT_TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = TAG_JOINT_COLUMN_NAME, referencedColumnName = TAG_JOINT_REFERENCED_COLUMN)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = TAG_INVERSE_JOINT_COLUMN_NAME, referencedColumnName = TAG_INVERSE_JOINT_REFERENCED_COLUMN)
            }
    )
    private List<Tag> tags;

    private String url;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
