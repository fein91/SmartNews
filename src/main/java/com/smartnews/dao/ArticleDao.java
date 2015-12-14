package com.smartnews.dao;

import com.smartnews.model.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> findByFolderId(long folderId, int start, int limit);

    Long save(Article article);

    Article findById(long articleId);

}
