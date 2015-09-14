package com.smartnews.rest.dto;

import java.util.List;

public class FolderDto extends NamedDto {

    private final FolderDto parent;
    private final List<FolderDto> children;
    private final List<ArticleDto> articles;

    private FolderDto(Builder builder) {
        super(builder.id, builder.name);
        this.parent = builder.parent;
        this.children = builder.children;
        this.articles = builder.articles;
    }

    public FolderDto getParent() {
        return parent;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }

    public List<FolderDto> getChildren() {
        return children;
    }

    public static Builder newBuilder(long id, String name) {
        return new FolderDto.Builder(id, name);
    }

    public static class Builder {
        private final long id;
        private final String name;
        private FolderDto parent;
        private List<FolderDto> children;
        private List<ArticleDto> articles;

        private Builder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder parent(FolderDto parent) {
            this.parent = parent;
            return this;
        }

        public Builder children(List<FolderDto> children) {
            this.children = children;
            return this;
        }

        public Builder articles(List<ArticleDto> articles) {
            this.articles = articles;
            return this;
        }

        public FolderDto build() {
            return new FolderDto(this);
        }
    }
}
