package com.smartnews.dao;

import com.smartnews.model.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> findPaginatedArticlesByFolderId(long folderId, int start, int limit);

}
