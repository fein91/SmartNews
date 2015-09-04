package com.smartnews.rest.mapper;

import com.smartnews.model.Article;
import com.smartnews.rest.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper implements RestMapper<ArticleDto, Article> {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public ArticleDto mapToDto(Article article) {
        return ArticleDto.newBuilder(article.getId(), article.getName())
                .url(article.getUrl())
                .description(article.getDescription())
                .tags(tagMapper.mapToDtos(article.getTags()))
                .build();
    }

    @Override
    public Article mapToEntity(ArticleDto dto) {
        return null;
    }

}
