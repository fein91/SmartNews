package com.smartnews.service;

import com.smartnews.dao.ArticleDao;
import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.rest.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional
    public Long createArticle(ArticleDto articleDto) {
        return articleDao.save(articleMapper.mapToEntity(articleDto));
    }
}
