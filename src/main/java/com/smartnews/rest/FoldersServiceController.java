package com.smartnews.rest;

import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.rest.exception.ResourceNotFoundException;
import com.smartnews.service.FoldersService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FoldersServiceController {

    private static final Logger LOG = LogManager.getLogger(FoldersServiceController.class.getName());

    private static final int MIN_PAGE_NUMBER = 1;
    private static final int MIN_PAGE_SIZE = 2;
    private static final String WRONG_PAGING_PARAMETERS_MSG = "Wrong paging params: page = %d, size = %d";
    private static final String LOADING_ARTICLES_MSG = "Loading articles from folder %d with params: page = %d, size = %d";

    @Autowired
    private FoldersService foldersService;

    @RequestMapping(value = "/folder/{folderId}/articles", method = RequestMethod.GET)
    public List<ArticleDto> getArticles(@PathVariable long folderId, @RequestParam("page") int page, @RequestParam("size") int size) {
        LOG.info(String.format(LOADING_ARTICLES_MSG, folderId, page, size));
        if (page < MIN_PAGE_NUMBER || size < MIN_PAGE_SIZE) {
            String errorMessage = String.format(WRONG_PAGING_PARAMETERS_MSG, page, size);
            LOG.warn(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }
        return foldersService.findArticlesByFolderID(folderId, page, size);
    }
}
