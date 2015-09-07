package com.smartnews.dao;

import com.smartnews.model.Folder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FolderDaoImpl extends AbstractDao implements FolderDao {
    @Override
    public void save(Folder folder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Folder> list() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Folder folder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Folder findById(long id) {
        return (Folder) getSession().get(Folder.class, id);
    }

    @Override
    public void update(Folder folder) {
        throw new UnsupportedOperationException();
    }
}
