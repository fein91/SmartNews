package com.smartnews.dao;

import com.smartnews.model.Article;
import com.smartnews.model.Client;
import com.smartnews.model.Folder;
import com.smartnews.model.Tag;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ClientDaoTest extends AbstractClientDaoTest {

    private static final String TEST_CLIENT_INSERT_GET =  "testInsertAndGetClient";
    private static final String TEST_UPDATE_CLIENT = "testUpdateClient";
    private static final String TEST_UPDATE_CLIENT_MODIFIED = "testUpdateClient modified";
    private static final String TEST_DELETE_CLIENT = "testDeleteClient";
    private static final String TEST_CLIENT_INSERT_WITH_FOLDERS =  "testInsertClientWithFolders";

    //insert-get
    @Test
    @Transactional
    @Rollback(true)
    public void testInsertAndGetClient() {
        Client testClient = new Client(TEST_CLIENT_INSERT_GET);
        clientDao.save(testClient);

        long testClientId = testClient.getId();
        Client dbTestClient = clientDao.findById(testClientId);

        assertEquals("client was inserted incorrectly", testClient, dbTestClient);
    }

    //insert-update-get
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateClient() {
        Client testClient = new Client(TEST_UPDATE_CLIENT);
        clientDao.save(testClient);

        testClient.setName(TEST_UPDATE_CLIENT_MODIFIED);
        clientDao.update(testClient);

        Client dbTestClient = clientDao.findById(testClient.getId());

        assertEquals("client wasn't updated ", dbTestClient.getName(), TEST_UPDATE_CLIENT_MODIFIED);
    }

    //insert-delete-get
    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteClient() {
        Client testClient = new Client(TEST_DELETE_CLIENT);
        clientDao.save(testClient);

        assertNotNull("client wasn't inserted ", clientDao.findById(testClient.getId()));

        clientDao.delete(testClient);

        assertNull("client wasn't deleted ", clientDao.findById(testClient.getId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCascadeInsertClient() {
        Client testClient = new Client(TEST_CLIENT_INSERT_WITH_FOLDERS);
        Folder videoFolder = new Folder();
        videoFolder.setName("video");
        Folder pronFolder = new Folder();
        pronFolder.setName("pron");
        pronFolder.setParentFolder(videoFolder);
        testClient.setFolders(Arrays.asList(videoFolder, pronFolder));


        Tag tag = new Tag();
        tag.setName("hot");

        Article article = new Article();
        article.setName("Hot pussies");
        article.setTags(Collections.singletonList(tag));

        pronFolder.setArticles(Collections.singletonList(article));

        clientDao.save(testClient);

        Client actualClient = clientDao.findById(testClient.getId());
        assertNotNull(actualClient);
    }
}
