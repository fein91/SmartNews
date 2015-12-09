package com.smartnews.dao;

import com.smartnews.model.Article;
import com.smartnews.model.Client;
import com.smartnews.model.Folder;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class ArticleDaoTest extends AbstractDaoTest {

    private static final String TEST_CLIENT_INSERT_WITH_FOLDERS =  "testInsertArticleToExistingFolder";

    @Test
    @Transactional
    @Rollback(true)
    public void testInsertArticleToExistingFolder() {
        Client testClient = new Client(TEST_CLIENT_INSERT_WITH_FOLDERS);
        Folder videoFolder = new Folder();
        videoFolder.setName("video");
        testClient.setFolders(Arrays.asList(videoFolder));

        clientDao.save(testClient);

        assertNotNull("video folder wasn't persisted", videoFolder.getId());

        Article testArticle = new Article();
        testArticle.setName("article");
        testArticle.setFolderFk(videoFolder.getId());
        articleDao.save(testArticle);
        assertNotNull("article wasn't persisted", testArticle.getId());
    }
}
