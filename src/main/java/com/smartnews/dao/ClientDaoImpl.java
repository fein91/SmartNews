package com.smartnews.dao;

import com.smartnews.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl extends AbstractDao implements ClientDao {

    @Override
    public void save(Client client) {
        getSession().save(client);
    }

    @Override
    public Client findById(long id) {
        return (Client) getSession().get(Client.class, id);
    }

    @Override
    public void update(Client client) {
        getSession().update(client);
    }

    @Override
    public List<Client> list() {
        return getSession().getNamedQuery(Client.FIND_ALL).list();
    }

    @Override
    public void delete(Client client) {
        getSession().delete(client);
    }
}
