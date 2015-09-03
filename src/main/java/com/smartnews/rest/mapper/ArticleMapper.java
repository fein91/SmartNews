package com.smartnews.rest.mapper;

import com.google.common.collect.Lists;
import com.smartnews.model.Article;
import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.rest.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ArticleDto> mapToDtos(List<Article> articles) {
        return articles.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Article mapToEntity(ArticleDto dto) {
        return null;
    }

    @Override
    public List<Article> mapToEntities(List<ArticleDto> dtos) {
        return null;
    }
}
