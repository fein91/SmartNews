package com.smartnews.service;

import com.smartnews.dao.AbstractDaoTest;
import com.smartnews.model.Client;
import com.smartnews.model.Folder;
import com.smartnews.rest.dto.ArticleDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

public class ArticlesServiceTest extends AbstractDaoTest {

    private final String TEST_ARTICLE_NAME = "jsoup HTML parser hello world examples";

    @Autowired
    private ArticlesService articlesService;

    @Test
    @Transactional
    @Rollback(true)
    public void createArticleTest() {
        Client testClient = new Client("test client");
        Folder videoFolder = new Folder();
        videoFolder.setName("video");

        clientDao.save(testClient);

        ArticleDto articleDto = ArticleDto.newBuilder(7777777, "", videoFolder.getId())
                .url("http://www.mkyong.com/java/jsoup-html-parser-hello-world-examples/")
                .build();
        Long articleId = articlesService.createArticle(articleDto);
        assertEquals(TEST_ARTICLE_NAME, articleDao.findById(articleId).getName());
    }
}
