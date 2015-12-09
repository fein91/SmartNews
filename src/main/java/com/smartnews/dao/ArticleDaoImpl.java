package com.smartnews.dao;

import com.smartnews.model.Article;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDaoImpl extends AbstractDao implements ArticleDao  {

    @Override
    public List<Article> findByFolderId(long folderId, int start, int limit) {
        return getSession().createCriteria(Article.class)
                .add(Restrictions.eq(Article.FOLDER_FK_PROPERTY, folderId))
                .setFirstResult(start)
                .setMaxResults(limit).list();
    }

    @Override
    public Long save(Article article) {
        return (Long) getSession().save(article);
    }

    @Override
    public Article findById(long articleId) {
        return (Article) getSession().get(Article.class, articleId);
    }
}
