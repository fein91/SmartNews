package com.smartnews.rest.mapper;

import com.smartnews.model.Article;
import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.rest.exception.ParseException;
import com.smartnews.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class ArticleMapper implements RestMapper<ArticleDto, Article> {

    private final static String UNABLE_TO_PARSE_URL = "Unable to parse url: %s";

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ParserService parserService;

    @Override
    public ArticleDto mapToDto(Article article) {
        return ArticleDto.newBuilder(article.getId(), article.getName(), article.getFolderFk())
                .url(article.getUrl())
                .description(article.getDescription())
                .tags(tagMapper.mapToDtos(article.getTags()))
                .build();
    }

    @Override
    public Article mapToEntity(ArticleDto articleDto) {
        String articleName;
        try {
            articleName = parserService.parseTitle(articleDto.getUrl());
        } catch (IOException e) {
            throw new ParseException(String.format(UNABLE_TO_PARSE_URL, articleDto.getUrl()), e);
        }
        return Article.newBuilder(articleName, articleDto.getFolderId())
                .url(articleDto.getUrl())
                .tags(tagMapper.mapToEntities(articleDto.getTags().orElse(new ArrayList<>())))
                .description(articleDto.getDescription())
                .build();
    }

}
