package com.smartnews.rest.dto;

import java.util.List;
import java.util.Optional;

public class ArticleDto extends NamedDto {
    private final long folderId;
    private final List<TagDto> tags;
    private final String url;
    private final String description;

    //dummy constructor for json parsing
    public ArticleDto() {
        super(0, null);
        this.folderId = 0;
        this.description = null;
        this.url = null;
        this.tags = null;
    }

    private ArticleDto(Builder builder) {
        super(builder.id, builder.name);
        this.tags = builder.tags;
        this.url = builder.url;
        this.description = builder.description;
        this.folderId = builder.folderId;
    }

    public Optional<List<TagDto>> getTags() {
        return Optional.ofNullable(tags);
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public long getFolderId() {
        return folderId;
    }

    public static Builder newBuilder(long id, String name, long folderId) {
        return new ArticleDto.Builder(id, name, folderId);
    }

    public static class Builder {
        private final long id;
        private final String name;
        private final long folderId;
        private List<TagDto> tags;
        private String url;
        private String description;

        private Builder(long id, String name, long folderId) {
            this.id = id;
            this.name = name;
            this.folderId = folderId;
        }

        public Builder tags(List<TagDto> tags) {
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

        public ArticleDto build() {
            return new ArticleDto(this);
        }
    }
}
