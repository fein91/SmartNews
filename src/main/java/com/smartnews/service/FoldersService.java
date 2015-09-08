package com.smartnews.service;

import com.smartnews.rest.dto.ArticleDto;

import java.util.List;

public interface FoldersService {
    int DEFAULT_PAGE = 1;
    int DEFAULT_PAGE_SIZE = 17;

    public List<ArticleDto> findArticlesByFolderID(long folderId, int page, int size);
}
