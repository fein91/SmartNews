package com.smartnews.dao;

import com.smartnews.model.Client;

import java.util.List;

public interface ClientDao {
    public void save(Client client);

    public List<Client> list();

    public void delete(Client client);

    public Client findById(long id);

    public void update(Client client);
}
