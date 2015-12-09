package com.smartnews.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public abstract class AbstractDaoTest {
    @Autowired
    protected ClientDao clientDao;

    @Autowired
    protected ArticleDao articleDao;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
