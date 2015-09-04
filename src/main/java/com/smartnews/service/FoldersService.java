package com.smartnews.service;

import com.smartnews.rest.dto.ArticleDto;

import java.util.List;

public interface FoldersService {
    public List<ArticleDto> findPaginatedArticlesByFolderID(long folderId, int page, int size);
}
