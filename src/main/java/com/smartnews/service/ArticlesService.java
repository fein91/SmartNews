package com.smartnews.service;

import com.smartnews.rest.dto.ArticleDto;

public interface ArticlesService {
    Long createArticle(ArticleDto articleDto);
}
