package com.smartnews.rest.mapper;

import com.smartnews.model.Client;
import com.smartnews.rest.dto.ClientDto;
import com.smartnews.rest.dto.FolderDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ClientDto> mapToDtos(List<Client> clients) {
        return clients.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Client mapToEntity(ClientDto clientDto) {
        return null;
    }

    @Override
    public List<Client> mapToEntities(List<ClientDto> dtos) {
        return null;
    }
}
