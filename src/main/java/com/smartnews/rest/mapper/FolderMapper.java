package com.smartnews.rest.mapper;

import com.smartnews.model.Folder;
import com.smartnews.rest.dto.FolderDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderMapper implements RestMapper<FolderDto, Folder> {

    private static final Logger LOG = LogManager.getLogger(FolderMapper.class.getName());

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public FolderDto mapToDto(Folder folder) {
        LOG.info("mapToDto start");
        FolderDto parentFolderDto = folder.getParentFolder() != null
                ? mapToDto(folder.getParentFolder())
                : null;

        FolderDto folderDto = new FolderDto(folder.getId(), folder.getName(), parentFolderDto,
                articleMapper.mapToDtos(folder.getArticles()));
        LOG.info("mapToDto finish");
        return folderDto;
    }

    @Override
    public Folder mapToEntity(FolderDto folderDto) {
        return null;
    }
}
