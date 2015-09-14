package com.smartnews.rest.mapper;

import com.smartnews.model.Folder;
import com.smartnews.rest.dto.FolderDto;
import com.smartnews.service.FoldersService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderMapper implements RestMapper<FolderDto, Folder> {

    private static final Logger LOG = LogManager.getLogger(FolderMapper.class.getName());

    @Autowired
    private FoldersService foldersService;

    @Override
    public FolderDto mapToDto(Folder folder) {
        return FolderDto.newBuilder(folder.getId(), folder.getName())
                .children(mapToDtos(folder.getSubFolders()))
                .articles(foldersService.findArticlesByFolderID(folder.getId(), FoldersService.DEFAULT_PAGE, FoldersService.DEFAULT_PAGE_SIZE))
                .build();
    }

    @Override
    public Folder mapToEntity(FolderDto folderDto) {
        return null;
    }
}
