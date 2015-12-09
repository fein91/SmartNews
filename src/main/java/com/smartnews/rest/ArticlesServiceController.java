package com.smartnews.rest;

import com.smartnews.rest.dto.ArticleDto;
import com.smartnews.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ArticlesServiceController {

    @Autowired
    private ArticlesService articlesService;

    @RequestMapping(value = "/article", method = RequestMethod.POST, consumes = "application/json")
    public void createArticle(@RequestBody ArticleDto articleDto) {
        articlesService.createArticle(articleDto);
    }
}
