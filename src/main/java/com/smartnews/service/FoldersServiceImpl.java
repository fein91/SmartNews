package com.smartnews.service;

import com.smartnews.dao.ArticleDao;
import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.rest.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoldersServiceImpl implements FoldersService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleMapper articleMapper;

    @Transactional
    public List<ArticleDto> findPaginatedArticlesByFolderID(long folderId, int page, int size) {
        int start = (page - 1) * size;
        return articleMapper.mapToDtos(articleDao.findByFolderId(folderId, start, size));
    }
}
