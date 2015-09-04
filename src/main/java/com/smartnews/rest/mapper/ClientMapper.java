package com.smartnews.rest.mapper;

import com.smartnews.model.Client;
import com.smartnews.rest.dto.ClientDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements RestMapper<ClientDto, Client> {

    private static final Logger LOG = LogManager.getLogger(ClientMapper.class.getName());

    @Autowired
    private FolderMapper folderMapper;

    @Override
    public ClientDto mapToDto(Client client) {
        return new ClientDto(client.getId(), client.getName(), folderMapper.mapToDtos(client.getFolders()));
    }

    @Override
    public Client mapToEntity(ClientDto clientDto) {
        return null;
    }

}
