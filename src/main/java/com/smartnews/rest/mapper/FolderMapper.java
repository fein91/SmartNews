package com.smartnews.rest.mapper;

import com.smartnews.model.Folder;
import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.rest.dto.FolderDto;
import com.smartnews.service.FoldersService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FolderMapper implements RestMapper<FolderDto, Folder> {

    private static final Logger LOG = LogManager.getLogger(FolderMapper.class.getName());

    @Autowired
    private FoldersService foldersService;

    @Override
    public FolderDto mapToDto(Folder folder) {
        FolderDto parentFolderDto = folder.getParentFolder() != null
                ? mapToDto(folder.getParentFolder())
                : null;
        List<ArticleDto> articles = foldersService.findArticlesByFolderID(folder.getId(), FoldersService.DEFAULT_PAGE, FoldersService.DEFAULT_PAGE_SIZE);
        return FolderDto.newBuilder(folder.getId(), folder.getName())
                .parent(parentFolderDto)
                .children(mapToDtos(folder.getSubFolders()))
                .articles(articles)
                .build();
    }

    @Override
    public Folder mapToEntity(FolderDto folderDto) {
        return null;
    }
}
