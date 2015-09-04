package com.smartnews.service;

import com.smartnews.dao.ClientDao;
import com.smartnews.model.Client;
import com.smartnews.rest.dto.ClientDto;
import com.smartnews.rest.mapper.ClientMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger LOG = LogManager.getLogger(ClientServiceImpl.class.getName());

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto findClient(long clientId) {
        Client client = clientDao.findById(clientId);
        return clientMapper.mapToDto(client);
    }

    @Override
    @Transactional
    public List<ClientDto> findAllClients() {
        return clientMapper.mapToDtos(clientDao.list());
    }
}
