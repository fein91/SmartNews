package com.smartnews.dao;

import com.smartnews.model.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> findByFolderId(long folderId, int start, int limit);

}
