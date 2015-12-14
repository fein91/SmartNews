package com.smartnews.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Article.TABLE_NAME)
@NamedQueries({@NamedQuery(name = Article.FIND_ALL, query = "select a from Article a")})
public class Article implements ModelEntity {
    public static final String FIND_ALL = "Article.findAll";
    public static final String TABLE_NAME = "article";
    private static final String SEQUENCE_GENERATOR = "article_seq_gen";
    private static final String SEQUENCE_NAME = "article_seq";
    private static final String FOLDER_FK = "folder_fk";
    public static final String FOLDER_FK_PROPERTY = "folderFk";
    private static final String TAG_JOINT_TABLE_NAME = "article_tag";
    private static final String TAG_JOINT_COLUMN_NAME = "article_fk";
    private static final String TAG_JOINT_REFERENCED_COLUMN = "id";
    private static final String TAG_INVERSE_JOINT_COLUMN_NAME = "tag_fk";
    private static final String TAG_INVERSE_JOINT_REFERENCED_COLUMN = "id";

    public Article() {
    }

    public Article(Builder builder) {
        this.name = builder.name;
        this.folderFk = builder.folderFk;
        this.tags = builder.tags;
        this.description = builder.description;
        this.url = builder.url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize=1)
    private long id;
    private String name;
    @Column(name = FOLDER_FK)
    private long folderFk;

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

    public long getFolderFk() {
        return folderFk;
    }

    public void setFolderFk(long folderFk) {
        this.folderFk = folderFk;
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

    public static Builder newBuilder(String name, long folderId) {
        return new Article.Builder(name, folderId);
    }

    public static class Builder {
        private final String name;
        private final long folderFk;
        private List<Tag> tags;
        private String url;
        private String description;

        private Builder(String name, long folderFk) {
            this.name = name;
            this.folderFk = folderFk;
        }

        public Builder tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }
}
